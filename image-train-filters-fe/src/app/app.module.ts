import {NgModule, CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {ImageComponent} from './image-loader/image.loader';

import {FileUploadModule} from 'ng2-file-upload';
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
    NbThemeModule,
} from '@nebular/theme';
import {RouterModule} from "@angular/router";
import {ImageLoaderChartizateComponent} from "./image-loader/image-loader-chartizate/image.loader.chartizate";
import {ImageLoaderContourComponent} from "./image-loader/image-loader-contour/image.loader.contour";
import {ImageLoaderKuwaharaComponent} from "./image-loader/image-loader-kuwahara/image.loader.kuwahara";
import {HttpClientModule} from "@angular/common/http";
import {NgxSliderModule} from "@angular-slider/ngx-slider";
import {ColorPickerDirective} from "ngx-color-picker";

@NgModule({
    bootstrap: [
        AppComponent,
    ],
    declarations: [
        AppComponent,
        ImageComponent,
        ImageLoaderChartizateComponent,
        ImageLoaderContourComponent,
        ImageLoaderKuwaharaComponent
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
        FileUploadModule,
        NbAlertModule,
        NbTabsetModule,
        NbSelectModule,
        HttpClientModule,
        NgxSliderModule,
        ColorPickerDirective,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],
})
export class AppModule {
}
