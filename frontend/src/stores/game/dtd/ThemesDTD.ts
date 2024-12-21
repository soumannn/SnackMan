//für ein Theme
export interface IThemeDTD{
    ground: string;
    wall:string;
    character:{
        snackman: string; 
        ghost: string;
    };
}
//für alle Themes
export interface IThemes {
    [key:string] : IThemeDTD, // der Schlüssel ist der themes name 
}
