import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {ImageComponent} from './image-loader/image.loader';

import {FileSelectDirective} from 'ng2-file-upload';
import {NbButtonModule, NbInputModule, NbLayoutModule, NbSidebarModule, NbThemeModule} from '@nebular/theme';
import {RouterModule} from "@angular/router";

@NgModule({
    imports: [BrowserModule, FormsModule, NbButtonModule, NbThemeModule.forRoot(), NbLayoutModule, RouterModule.forRoot([{
        path: '',
        component: AppComponent
    },
    ], {useHash: true}), NbSidebarModule.forRoot(), NbInputModule],
    declarations: [AppComponent, ImageComponent, FileSelectDirective],
    bootstrap: [AppComponent]
})
export class AppModule {
}
