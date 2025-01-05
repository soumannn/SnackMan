export interface IThemeDTD {
    ground: string;
    wall: string;
    character: {
        snackman:  string | { path: string; scale: number };
        ghost: string | { path: string; scale: number };
    };
}

export interface IThemes {
    [key: string]: IThemeDTD; // der Schlüssel ist der Theme-Name
}
