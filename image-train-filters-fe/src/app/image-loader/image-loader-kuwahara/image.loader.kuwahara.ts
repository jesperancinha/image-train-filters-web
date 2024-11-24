import {Component, OnInit} from '@angular/core';
import {ItfKuwaharaCommand} from "../../command-types/itf-kuwahara-command";
import {Command} from "../../command-types/command";

@Component({
    selector: 'image-loader-kuwaharara',
    styleUrls: ['./image.loader.kuwahara.css'],
    templateUrl: './image.loader.kuwahara.html',
    standalone: false
})
export class ImageLoaderKuwaharaComponent implements OnInit {

    public commands: ItfKuwaharaCommand[] = [];
    public itfKuwaharaCommand: ItfKuwaharaCommand = new ItfKuwaharaCommand();
    public loading: boolean;

    constructor() {
        this.loading = false;
        this.itfKuwaharaCommand.squareSize = 2;
        this.itfKuwaharaCommand.iterations = 2;
        this.commands.push(this.itfKuwaharaCommand);
    }

    public ngOnInit(): void {
    }

    public getConfiguration(): Command[] {
        return this.commands.map(command => {
            const newCommand = new Command();
            newCommand.filter = "imageKuwahara";
            newCommand.settings = [];
            newCommand.settings.push({name: "square-size", value: String(command.squareSize)});
            newCommand.settings.push({name: "iterations", value: String(command.iterations)});
            return newCommand;
        });
    }
}
