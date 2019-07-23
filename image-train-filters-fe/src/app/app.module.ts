import {NgModule} from '@angular/core';
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
    NbThemeModule
} from '@nebular/theme';
import {RouterModule} from "@angular/router";
import {ColorPickerModule} from "ngx-color-picker";
import {ImageLoaderChartizateComponent} from "./image-loader/image-loader-chartizate/image.loader.chartizate";
import {ImageLoaderContourComponent} from "./image-loader/image-loader-contour/image.loader.contour";
import {ImageLoaderKuwaharaComponent} from "./image-loader/image-loader-kuwahara/image.loader.kuwahara";
import {HttpClientModule} from "@angular/common/http";
import {Ng5SliderModule} from "ng5-slider";

@NgModule({
    declarations: [AppComponent, ImageComponent, ImageLoaderChartizateComponent, ImageLoaderContourComponent, ImageLoaderKuwaharaComponent],
    imports: [BrowserModule, FormsModule, NbButtonModule, NbThemeModule.forRoot(), NbLayoutModule, RouterModule.forRoot([{
        component: AppComponent,
        path: '',
    },], {useHash: true}), NbSidebarModule.forRoot(), NbInputModule, NbCardModule, NbSpinnerModule,
        FileUploadModule, NbAlertModule, NbTabsetModule, ColorPickerModule, NbSelectModule, HttpClientModule, Ng5SliderModule],
    bootstrap: [AppComponent],
})
export class AppModule {
}
