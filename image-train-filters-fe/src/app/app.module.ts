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

@NgModule({
    imports: [BrowserModule, FormsModule, NbButtonModule, NbThemeModule.forRoot(), NbLayoutModule, RouterModule.forRoot([{
        path: '',
        component: AppComponent
    },
    ], {useHash: true}), NbSidebarModule.forRoot(), NbInputModule, NbCardModule, NbSpinnerModule, FileUploadModule, NbAlertModule, NbTabsetModule, ColorPickerModule],
    declarations: [AppComponent, ImageComponent, ImageLoaderChartizateComponent, ImageLoaderContourComponent, ImageLoaderKuwaharaComponent],
    bootstrap: [AppComponent]
})
export class AppModule {
}
