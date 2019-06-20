import {Component, OnInit} from '@angular/core';
import {FileUploader} from 'ng2-file-upload/ng2-file-upload';
import {DomSanitizer} from "@angular/platform-browser";

const URL: string = 'http://127.0.0.1:4200/api/images';

class ImageChangeEvent {
    target: any;
    files: [];

}

@Component({
    selector: 'image-loader',
    templateUrl: './image.loader.html',
    styleUrls: ['./image.loader.css']
})
export class ImageComponent implements OnInit {
    public uploader: FileUploader;

    private imageToShow: any;
    private filename: String;
    private loading: boolean;

    constructor(public domSanitizer: DomSanitizer) {
        this.loading = false;
    }

    ngOnInit() {
        this.uploader = new FileUploader({
            url: URL,
            method: 'post',
            itemAlias: 'filename',
            disableMultipart: false//,
        });
        this.uploader.onBuildItemForm = (fileItem: any, form: any) => {
            form.append('commands', "{ \"commands\": [ { \"filter\": \"imageKuwahara\", \"settings\": [ { \"name\": \"square-size\", \"value\": \"2\"}, { \"name\": \"iterations\", \"value\": \"2\"} ]}, { \"filter\": \"imageContour\", \"settings\": [ { \"name\": \"bgColor\", \"value\": \"0xFFFFFF\"}, { \"name\": \"lnColor\", \"value\": \"0x000000\"}, { \"name\": \"diffThreshold\", \"value\": \"800000\"}, { \"name\": \"radius\", \"value\": \"2\"} ]} ] }");
        };
        this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
            this.imageToShow = this.domSanitizer.bypassSecurityTrustUrl("data:image/png;base64, " + response);
            this.loading = false;
        };
    }

    imageChanged($event?: ImageChangeEvent) {
        if ($event) {
            this.filename = $event.target.files[0].name;
        }
        this.imageToShow = null;
    }

    loadImage() {
        this.loading = true;
        this.uploader.uploadAll();
    }
}
