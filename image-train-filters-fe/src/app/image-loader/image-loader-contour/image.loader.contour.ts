import {Component, OnInit, Output} from '@angular/core';
import {ItfContourCommand} from "../../command-types/itf-contour-command";
import {Command} from "../../command-types/command";

const DEFAULT_BACKGROUND_COLOR = "#ffffff";
const DEFAULT_LINE_COLOR = "#000000";
const DEFAULT_DIFF_THRESHOLD = 800000;
const DEFAULT_RADIUS = 2;

@Component({
    selector: 'image-loader-contour',
    styleUrls: ['./image.loader.contour.css'],
    templateUrl: './image.loader.contour.html',
})
export class ImageLoaderContourComponent implements OnInit {
    public loading: boolean;
    public commands: ItfContourCommand[] = [];
    public itfContourCommand: ItfContourCommand = new ItfContourCommand();

    @Output() configuration: Command[] = this.getConfiguration();

    constructor() {
        this.loading = false;
        this.itfContourCommand.bgColor = DEFAULT_BACKGROUND_COLOR;
        this.itfContourCommand.lnColor = DEFAULT_LINE_COLOR;
        this.itfContourCommand.diffThreshold = DEFAULT_DIFF_THRESHOLD;
        this.itfContourCommand.radius = DEFAULT_RADIUS;
        this.commands.push(this.itfContourCommand);
    }

    public ngOnInit(): void {

    }

    public getConfiguration(): Command[] {
        return this.commands.map(command => {
            const newCommand = new Command();
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
