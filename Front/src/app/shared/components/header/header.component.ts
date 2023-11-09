import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
// import { CartComponent } from './../cart/cart.component';
// import { CartServicesService } from 'src/app/services/cart-services.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  name: any;

  constructor(public cartModal: MatDialog,
    // private cartService: CartServicesService,
    private router: Router, private route: ActivatedRoute
  ) {
    // this.cartService.getCart()
  }


  ngOnInit(): void {

    this.route.queryParamMap.subscribe(params => {
      // Retrieve the query parameter value from the URL
      this.name = params.get('name');
    });
  }

  // openCart() {
  //   this.cartModal.open(CartComponent)
  // }

  // search(searchValue: string) {
  //   this.router.navigate(['/search-products'], { queryParams: { name: searchValue } });
  // }

  // cartProductsNumber():number{
  //   return this.cartService.cartProductsNumber()
  // }

}
