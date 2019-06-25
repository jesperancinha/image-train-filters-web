import {Component, OnInit} from '@angular/core';
import {FileUploader} from 'ng2-file-upload/ng2-file-upload';
import {DomSanitizer} from "@angular/platform-browser";

const URL: string = '/api/images';

class ImageChangeEvent extends Event {
    target: any;
    files: any;
}

@Component({
    selector: 'image-loade-kuwaharara',
    templateUrl: './image.loader.kuwahara.html',
    styleUrls: ['./image.loader.kuwahara.css']
})
export class ImageLoaderKuwaharaComponent implements OnInit {

    loading: boolean;

    constructor() {
        this.loading = false;
    }

    ngOnInit() {
    }

}
