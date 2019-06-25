import {Component, OnInit} from '@angular/core';
import {ItfCommand, ItfContourCommand} from "../command.types";

@Component({
    selector: 'image-loader-contour',
    templateUrl: './image.loader.contour.html',
    styleUrls: ['./image.loader.contour.css']
})
export class ImageLoaderContourComponent implements OnInit {
    loading: boolean;
    commands: ItfCommand[] = [];
    itfContourCommand: ItfContourCommand = new ItfContourCommand();

    constructor() {
        this.loading = false;
        this.itfContourCommand.bgColor ="#ffffff";
        this.itfContourCommand.lnColor ="#000000";
        this.itfContourCommand.diffThreshold =800000;
        this.itfContourCommand.radius =2;
        this.commands.push(this.itfContourCommand);
    }

    ngOnInit() {
    }
}
