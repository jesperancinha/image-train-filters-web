class ImageChangeEvent extends Event {
    public target: HTMLInputElement & EventTarget;
    public files: FileList;
}

export {ImageChangeEvent};
