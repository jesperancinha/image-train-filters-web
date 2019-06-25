import {Component, OnInit} from '@angular/core';
import {ItfCommand, ItfKuwaharaCommand} from "../command.types";

@Component({
    selector: 'image-loade-kuwaharara',
    templateUrl: './image.loader.kuwahara.html',
    styleUrls: ['./image.loader.kuwahara.css']
})
export class ImageLoaderKuwaharaComponent implements OnInit {

    commands: ItfCommand[] = [];
    itfKuwaharaCommand: ItfKuwaharaCommand = new ItfKuwaharaCommand();
    loading: boolean;

    constructor() {
        this.loading = false;
        this.itfKuwaharaCommand.squareSize = 2;
        this.itfKuwaharaCommand.iterations = 2;
        this.commands.push(this.itfKuwaharaCommand);
    }

    ngOnInit() {
    }

}
