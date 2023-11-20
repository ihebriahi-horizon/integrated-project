import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { CartComponent } from 'src/app/components/cart/cart.component';
import { CartServicesService } from '../../services/cart-services.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  name: any;

  constructor(
    public cartModal: MatDialog,
    private cartService: CartServicesService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.cartService.getCart();
  }

  ngOnInit(): void {
    this.route.queryParamMap.subscribe((params) => {
      // Retrieve the query parameter value from the URL
      this.name = params.get('name');
    });
  }

  openCart() {
    this.cartModal.open(CartComponent);
  }

  search(searchValue: string) {
    this.router.navigate(['/search-products'], {
      queryParams: { name: searchValue },
    });
  }

  cartProductsNumber(): number {
    return this.cartService.cartProductsNumber();
  }
}
