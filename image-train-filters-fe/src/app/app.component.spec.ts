import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AppComponent} from './app.component';
import {ImageComponent} from "./image-loader/image.loader";
import {ImageLoaderChartizateComponent} from "./image-loader/image-loader-chartizate/image.loader.chartizate";
import {ImageLoaderContourComponent} from "./image-loader/image-loader-contour/image.loader.contour";
import {ImageLoaderKuwaharaComponent} from "./image-loader/image-loader-kuwahara/image.loader.kuwahara";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {
    NbAlertModule,
    NbButtonModule,
    NbCardModule,
    NbInputModule,
    NbLayoutModule,
    NbSelectModule,
    NbSidebarModule,
    NbSpinnerModule,
    NbTabsetModule,
    NbThemeModule
} from "@nebular/theme";
import {RouterModule} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import {ColorPickerDirective} from "ngx-color-picker";
import {NgxSliderModule} from "@angular-slider/ngx-slider";


describe('AppComponent', () => {
    let component: AppComponent;
    let fixture: ComponentFixture<AppComponent>;

    beforeEach((() => {

        TestBed.configureTestingModule({
            declarations: [
                AppComponent,
                ImageComponent,
                ImageLoaderChartizateComponent,
                ImageLoaderContourComponent,
                ImageLoaderKuwaharaComponent,
            ],
            imports: [
                BrowserModule,
                FormsModule,
                NbButtonModule,
                NbThemeModule.forRoot(),
                NbLayoutModule,
                RouterModule.forRoot([{
                    component: AppComponent,
                    path: '',
                },], {useHash: true}),
                NbSidebarModule.forRoot(),
                NbInputModule,
                NbCardModule,
                NbSpinnerModule,
                NbAlertModule,
                NbTabsetModule,
                NbSelectModule,
                HttpClientModule,
                NgxSliderModule,
                ColorPickerDirective,
            ],
        })
            .compileComponents();
        fixture = TestBed.createComponent(AppComponent)
        component = fixture.componentInstance;
        fixture.detectChanges();
    }));


    it('should create', () => {
        expect(component).toBeTruthy();
    });
});