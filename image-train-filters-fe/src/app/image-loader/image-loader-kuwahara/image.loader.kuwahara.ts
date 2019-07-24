import {Component, OnInit} from '@angular/core';
import {Command, ItfKuwaharaCommand} from "../command.types";

@Component({
    selector: 'image-loader-kuwaharara',
    templateUrl: './image.loader.kuwahara.html',
    styleUrls: ['./image.loader.kuwahara.css']
})
export class ImageLoaderKuwaharaComponent implements OnInit {

    commands: ItfKuwaharaCommand[] = [];
    itfKuwaharaCommand: ItfKuwaharaCommand = new ItfKuwaharaCommand();
    loading: boolean;

    constructor() {
        this.loading = false;
        this.itfKuwaharaCommand.squareSize = 2;
        this.itfKuwaharaCommand.iterations = 2;
        this.commands.push(this.itfKuwaharaCommand);
    }

    public ngOnInit(): void {
    }

    getConfiguration() {
        return this.commands.map(command => {
            let newCommand = new Command();
            newCommand.filter = "imageKuwahara";
            newCommand.settings = [];
            newCommand.settings.push({name: "square-size", value: String(command.squareSize)});
            newCommand.settings.push({name: "iterations", value: String(command.iterations)});
            return newCommand;
        });
    }
}
