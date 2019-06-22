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
    NbThemeModule
} from '@nebular/theme';
import {RouterModule} from "@angular/router";

@NgModule({
    imports: [BrowserModule, FormsModule, NbButtonModule, NbThemeModule.forRoot(), NbLayoutModule, RouterModule.forRoot([{
        path: '',
        component: AppComponent
    },
    ], {useHash: true}), NbSidebarModule.forRoot(), NbInputModule, NbCardModule, NbSpinnerModule, FileUploadModule, NbAlertModule],
    declarations: [AppComponent, ImageComponent],
    bootstrap: [AppComponent]
})
export class AppModule {
}
