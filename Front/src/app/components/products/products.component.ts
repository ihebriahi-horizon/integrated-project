import { Observable, tap } from 'rxjs';
import {
  AfterViewInit,
  Component,
  ElementRef,
  OnInit,
  ViewChild,
} from '@angular/core';
import {
  AbstractControl,
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  NgForm,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSlider, MatSliderThumb } from '@angular/material/slider';
import { StoreServicesService } from 'src/app/shared/services/store-services.service';
import { CartServicesService } from 'src/app/shared/services/cart-services.service';
import { Cart } from 'src/app/shared/interfaces/cart';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss'],
})
export class ProductsComponent implements OnInit {
  @ViewChild(MatSlider) slider: MatSlider;

  asideVisible = true;

  @ViewChild('container', { static: false })
  container!: ElementRef;

  @ViewChild('create') create!: NgForm;
  store$!: Observable<any>;
  cart!: Cart;
  vetor: any;
  displayCount = 6;
  showAll = false;
  currentPage = 0;
  totalPages: number = 0;
  gender: any;
  productTypes!: any[];
  productColors!: any[];
  minProductPrice!: number;
  maxProductPrice!: number;

  searchForm!: FormGroup;
  productName: string;
  productType: string;
  productMinPrice: number;
  productMaxPrice: number;
  colorsArrayParam: string[];

  constructor(
    private storeService: StoreServicesService,
    private cartService: CartServicesService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private elRef: ElementRef,
    private router: Router
  ) {}

  ngOnInit(): void {
    // get the show filters button element
    const showFiltersBtn = document.querySelector(
      '.show-filters'
    ) as HTMLElement;

    // add event listener to show filters button
    showFiltersBtn.addEventListener('click', function () {
      // get the product filter element
      const productFilter = document.querySelector(
        '.product-filter'
      ) as HTMLElement;

      // toggle the display of the product filter element
      productFilter.classList.toggle('show');
    });

    this.getProductTypes();
    this.getProductColors();
    this.getMinProductPrice();
    this.getMaxProductPrice();

    this.route.queryParamMap.subscribe((params) => {
      // Retrieve the query parameter value from the URL
      this.gender = params.get('gender') != null ? params.get('gender')! : '';
      let page = Number(params.get('page'));
      this.currentPage = page;

      this.productName = params.get('name') != null ? params.get('name')! : '';
      this.productType = params.get('type') != null ? params.get('type')! : '';
      this.productMinPrice =
        params.get('minPrice') != null ? Number(params.get('minPrice'))! : 0;
      this.productMaxPrice =
        params.get('maxPrice') != null ? Number(params.get('maxPrice'))! : 0;

      let colorsString =
        params.get('colors') != null ? params.get('colors')! : '';
      this.colorsArrayParam = colorsString ? colorsString.split(',') : [];

      this.searchProducts(
        this.gender,
        page,
        this.productName,
        this.productType,
        colorsString,
        this.productMinPrice,
        this.productMaxPrice
      );
    });

    this.searchForm = this.formBuilder.group({
      productName: [this.productName],
      productFor: [this.gender],
      productType: [this.productType],
      productColorSet: new FormArray([]),
      productMinPrice: [this.productMinPrice],
      productMaxPrice: [this.productMaxPrice],
    });
  }

  toggleAside() {
    const asideEl = this.elRef.nativeElement.querySelector('.aside');
    if (asideEl) {
      asideEl.classList.toggle('hidden');
    }
  }

  scrollToTop() {
    this.container.nativeElement.scrollIntoView(true);
  }

  searchProducts(
    gender: string,
    page: number,
    name: string,
    type: string,
    colors: string,
    minPrice: number,
    maxPrice: number
  ) {
    this.store$ = this.storeService
      .searchProducts(gender, page, name, type, colors, minPrice, maxPrice)
      .pipe(
        tap((response) => {
          this.vetor = response.content;
          this.totalPages = response.totalPages;
        })
      );
  }
  getCurrentUrl() {
    return '/products';
  }

  addProductToCart(
    _id: number,
    name: string,
    price: number,
    size: number,
    quant: number,
    total: number
  ) {
    const products: Cart = { _id, name, price, size, quant, total };
    this.cartService.addToCart(products);
  }

  range() {
    return Array(this.totalPages)
      .fill(0)
      .map((x, i) => i + 1);
  }

  previousPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.router.navigate(['/products'], {
        queryParams: { gender: this.gender, page: this.currentPage },
      });
    }
  }

  nextPage() {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.router.navigate(['/products'], {
        queryParams: { gender: this.gender, page: this.currentPage },
      });
    }
  }

  goToPage(page: number) {
    this.currentPage = page;
  }

  getProductTypes() {
    this.storeService.getProductTypes().subscribe((data) => {
      this.productTypes = data;
    });
  }

  getProductColors() {
    this.storeService.getProductColors().subscribe((data) => {
      this.productColors = data;
      this.productColors.forEach((color) => {
        if (this.colorsArrayParam.indexOf(color) !== -1) {
          this.colorsFormArray.push(new FormControl(true));
        } else {
          this.colorsFormArray.push(new FormControl(false));
        }
      });
    });
  }

  getMinProductPrice() {
    this.storeService.getMinProductPrice().subscribe((data) => {
      this.minProductPrice = data;
    });
  }

  getMaxProductPrice() {
    this.storeService.getMaxProductPrice().subscribe((data) => {
      this.maxProductPrice = data;
    });
  }

  get colorsFormArray() {
    return this.searchForm.controls['productColorSet'] as FormArray;
  }

  changeMinProductPrice(inputElement: EventTarget) {
    const target = inputElement as HTMLInputElement;
    this.productMinPrice = Number(target.value);
    this.searchForm.controls['productMinPrice'].setValue(Number(target.value));
  }

  changeMaxProductPrice(inputElement: EventTarget) {
    const target = inputElement as HTMLInputElement;
    this.productMaxPrice = Number(target.value);
    this.searchForm.controls['productMaxPrice'].setValue(Number(target.value));
  }

  onSubmit() {
    const selectedColors = this.searchForm.value.productColorSet
      .map((checked, i) => (checked ? this.productColors[i] : null))
      .filter((v) => v !== null);
    const colorsQueryParam = selectedColors.join(',');
    this.router.navigate(['/products'], {
      queryParams: {
        gender: this.searchForm.value.productFor,
        page: this.currentPage,
        name: this.searchForm.value.productName,
        type: this.searchForm.value.productType,
        minPrice: this.searchForm.value.productMinPrice,
        maxPrice: this.searchForm.value.productMaxPrice,
        colors: colorsQueryParam,
      },
    });
  }

  reset() {
    this.searchForm = this.formBuilder.group({
      productName: [''],
      productFor: [''],
      productType: [''],
      productColorSet: new FormArray([]),
      productMinPrice: [''],
      productMaxPrice: [''],
    });
    this.getProductColors();

    this.router.navigate(['/products']);
  }

  //convenience getter for easy access to form fields
  get form(): { [key: string]: AbstractControl } {
    return this.searchForm.controls;
  }
}
