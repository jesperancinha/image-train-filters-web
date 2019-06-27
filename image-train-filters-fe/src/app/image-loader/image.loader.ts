import {Component, OnInit} from '@angular/core';
import {FileUploader} from 'ng2-file-upload/ng2-file-upload';
import {DomSanitizer} from "@angular/platform-browser";
import {Command} from "./command.types";
import {NbTabComponent} from "@nebular/theme";
import {ImageLoaderContourComponent} from "./image-loader-contour/image.loader.contour";
import {ImageLoaderKuwaharaComponent} from "./image-loader-kuwahara/image.loader.kuwahara";
import {ImageLoaderChartizateComponent} from "./image-loader-chartizate/image.loader.chartizate";

const URL: string = '/api/images';

class ImageChangeEvent extends Event {
    target: any;
    files: any;
}

@Component({
    selector: 'image-loader',
    templateUrl: './image.loader.html',
    styleUrls: ['./image.loader.css']
})
export class ImageComponent implements OnInit {
    public uploader: FileUploader;

    imageToShow: any;
    filename: String;
    loading: boolean;
    errorText: String;
    errorStatus: String;
    adviceText: String;
    fileUrl: any;
    commands: Command[];
    private currentTab: NbTabComponent;
    private ilcontour: ImageLoaderContourComponent;
    private ilkuwahara: ImageLoaderKuwaharaComponent;
    private ilchartizate: ImageLoaderChartizateComponent;
    selectedFile: any;
    imagePreview: any;

    constructor(public domSanitizer: DomSanitizer) {
        this.loading = false;
    }

    ngOnInit() {
        this.uploader = ImageComponent.createFileUploader();
        this.uploader.onBuildItemForm = (fileItem: any, form: any) => {
            switch (this.currentTab.tabTitle) {
                case "Contour":
                    this.commands = this.ilcontour.getConfiguration();
                    break;
                case "Kuwahara":
                    this.commands = this.ilkuwahara.getConfiguration();
                    break;
                case "Chartizate":
                    this.commands = this.ilchartizate.getConfiguration();
                    break;

            }
            form.append('commands', JSON.stringify({commands: this.commands}));
        };
        this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
            this.removeAllElementsFromQueue();
            if (status != 200) {
                this.errorStatus = status;
                this.errorText = response;
                if (status === 503 || status === 502) {
                    this.adviceText = "The complexity of your picture is too heavy for the current algorithm implementation. Unfortunately it requires more resources than the ones available.";
                } else if (status === 504 || status === 404) {
                    this.adviceText = "We apologize, but our services are momentarily down and we cannot process your request. Please try again later..."
                }
            } else {
                let generatedImage = this.domSanitizer.bypassSecurityTrustUrl("data:image/png;base64, " + response);
                this.fileUrl = generatedImage;
                this.imageToShow = generatedImage;
            }
            this.loading = false;
        };
    }

    private static createFileUploader() {
        return new FileUploader({
            url: URL,
            method: 'post',
            itemAlias: 'filename',
            disableMultipart: false,
            queueLimit: 1,
            maxFileSize: 100000000,
            allowedFileType: ['image']
        });
    }

    imageChanged($event?: Event) {
        this.resetAllMainControls();
        if ($event) {
            let file = (<ImageChangeEvent>$event).target.files[0];
            if (this.selectedFile && this.uploader.getNotUploadedItems().length === 0) {
                this.errorText = "Invalid file selection!";
                this.errorStatus = "Error!";
                this.adviceText = "Please select images file types only. Verified accepted types are jpg, jpeg and png. You are very welcomed to try other image types if you like";
                this.filename = file.name;
                this.imagePreview = null;
            } else if (file) {
                this.imagePreview = this.domSanitizer.bypassSecurityTrustUrl((window.URL.createObjectURL(file)));
                this.filename = file.name;
            } else {
                this.filename = null;
                this.imagePreview = null;
                this.removeAllElementsFromQueue();
            }
        }
        this.imageToShow = null;
    }

    resetAll() {
        this.removeAllElementsFromQueue();
        this.resetAllControls();
    }

    loadImage() {
        this.resetAllMainControls();
        this.loading = true;
        this.uploader.uploadAll();
    }

    tabChanged(tab: NbTabComponent, ilcontour: ImageLoaderContourComponent, ilkuwahara: ImageLoaderKuwaharaComponent, ilchartizate: ImageLoaderChartizateComponent) {
        this.commands = [];
        this.currentTab = tab;
        this.ilcontour = ilcontour;
        this.ilkuwahara = ilkuwahara;
        this.ilchartizate = ilchartizate;
    }

    private resetAllControls() {
        this.filename = null;
        this.imagePreview = null;
        this.resetAllMainControls();
    }

    private resetAllMainControls() {
        this.adviceText = null;
        this.errorStatus = null;
        this.errorText = null;

    }

    private removeAllElementsFromQueue() {
        this.uploader.cancelAll();
        this.uploader.clearQueue();
        this.selectedFile = null;
    }
}
