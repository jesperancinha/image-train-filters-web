import {Component, OnInit, Output} from '@angular/core';
import {Command, ItfContourCommand} from "../command.types";

@Component({
    selector: 'image-loader-contour',
    templateUrl: './image.loader.contour.html',
    styleUrls: ['./image.loader.contour.css']
})
export class ImageLoaderContourComponent implements OnInit {
    loading: boolean;
    commands: ItfContourCommand[] = [];
    itfContourCommand: ItfContourCommand = new ItfContourCommand();

    @Output() configuration: Command[] = this.getConfiguration();

    constructor() {
        this.loading = false;
        this.itfContourCommand.bgColor = "#ffffff";
        this.itfContourCommand.lnColor = "#000000";
        this.itfContourCommand.diffThreshold = 800000;
        this.itfContourCommand.radius = 2;
        this.commands.push(this.itfContourCommand);
    }

    ngOnInit() {

    }

    getConfiguration() {
        return this.commands.map(command => {
            let newCommand = new Command();
            newCommand.filter = "imageContour";
            newCommand.settings = [];
            newCommand.settings.push({name: "bgColor", value: command.bgColor});
            newCommand.settings.push({name: "lnColor", value: command.lnColor});
            newCommand.settings.push({name: "diffThreshold", value: String(command.diffThreshold)});
            newCommand.settings.push({name: "radius", value: String(command.radius)});
            return newCommand;
        });
    }
}
