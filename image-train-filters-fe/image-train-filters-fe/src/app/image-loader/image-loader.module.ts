import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ImageComponent } from './image.loader';
import { ImageLoaderChartizateComponent } from './image-loader-chartizate/image.loader.chartizate';
import { ImageLoaderContourComponent } from './image-loader-contour/image.loader.contour';
import { ImageLoaderKuwaharaComponent } from './image-loader-kuwahara/image.loader.kuwahara';
import { FileUploadModule } from 'ng2-file-upload';
import { FormsModule } from '@angular/forms';
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
} from '@nebular/theme';

@NgModule({
    declarations: [
        ImageComponent,
        ImageLoaderChartizateComponent,
        ImageLoaderContourComponent,
        ImageLoaderKuwaharaComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        FileUploadModule,
        NbButtonModule,
        NbCardModule,
        NbInputModule,
        NbSpinnerModule,
        NbAlertModule,
        NbTabsetModule,
        NbSelectModule,
    ],
    exports: [
        ImageComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImageLoaderModule { }