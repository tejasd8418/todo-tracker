import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AccountsComponent } from './accounts/accounts.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatCardModule } from '@angular/material/card';
import { LoginComponent } from './accounts/login/login.component';
import { RegisterComponent } from './accounts/register/register.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { CreateTodoComponent } from './home/create-todo/create-todo.component';
import { CategoryListComponent } from './home/category-list/category-list.component';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatTabsModule} from '@angular/material/tabs';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { CommonModule } from '@angular/common';
import {MatDialogModule} from '@angular/material/dialog';

import { NgxMasonryComponent, NgxMasonryModule } from 'ngx-masonry';

import {MatMenuModule} from '@angular/material/menu';
import { CreateCategoryComponent } from './home/category-list/create-category/create-category.component';
import { initializeApp,provideFirebaseApp } from '@angular/fire/app';
import { environment } from '../environments/environment';
import { provideAnalytics,getAnalytics,ScreenTrackingService,UserTrackingService } from '@angular/fire/analytics';
import { provideAuth,getAuth } from '@angular/fire/auth';
import { provideDatabase,getDatabase } from '@angular/fire/database';
import { provideFirestore,getFirestore } from '@angular/fire/firestore';
import { provideFunctions,getFunctions } from '@angular/fire/functions';
import { provideMessaging,getMessaging } from '@angular/fire/messaging';
import { providePerformance,getPerformance } from '@angular/fire/performance';
import { provideRemoteConfig,getRemoteConfig } from '@angular/fire/remote-config';
import { provideStorage,getStorage } from '@angular/fire/storage';

import { AuthHttpInterceptorService } from './service/httpInterceptor/auth-http-interceptor.service';
import { TodoComponent } from './home/show-todos/todo/todo.component';
import { AddGuestComponent } from './home/show-todos/todo/add-guest/add-guest.component';
import { ArchivesComponent } from './home/show-todos/archives/archives.component';
import { CompletedComponent } from './home/show-todos/completed/completed.component';
import { ShowTodosComponent } from './home/show-todos/show-todos.component';
import { AboutUsComponent } from './footer/about-us/about-us.component';
import { PrivacyPolicyComponent } from './footer/privacy-policy/privacy-policy.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';





@NgModule({
  declarations: [
    AppComponent,
    AccountsComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    CreateTodoComponent,
    CategoryListComponent,
    TodoComponent,
    CreateCategoryComponent,
    AddGuestComponent,
    ArchivesComponent,
    CompletedComponent,
    ShowTodosComponent,
    AboutUsComponent,
    PrivacyPolicyComponent
    ],
  imports: [

    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    MatCardModule,
    MatGridListModule,
    MatButtonToggleModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTabsModule,
    MatAutocompleteModule,
    CommonModule,
    MatDialogModule,
    NgxMasonryModule,
    MatMenuModule,
    MatSnackBarModule

    
  ],
  providers: [

    CategoryListComponent, 
    { 
      provide:HTTP_INTERCEPTORS, 
      useClass: AuthHttpInterceptorService, 
      multi:true 
    },
    TodoComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
