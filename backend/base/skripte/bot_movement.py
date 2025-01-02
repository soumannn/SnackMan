# -*- coding: utf-8 -*-
import random

SPEED = 1.0

def move_chicken(xPosition, yPosition, umgebungsdaten = None):
    """

    Bewegt Huhn in eine zufällige Ziellose Richtung

    xPosition: Aktuelle x-Koordinate des Huhns
    yPosition: Aktuelle y-Koordinate des Huhns
    umgebungsdaten: Daten über die Umgebung (Beschränkte Api 3*3 MapTiles)

    """

    # Zuerst ein  zufälliges Bewegungsmuster zum Testen
    newX = xPosition
    newY = yPosition

    moves = ["up", "down", "left", "right"]

    move = random.choice(moves)

    
    if move == "up":
        newY += 1
    elif move == "down":
        newY -= 1
    elif move == "left":
        newX -= 1
    elif move == "right":
        newX += 1

    # Validierung auf umgebungsdaten hier ? ToDo Aron

    return (newX, newY)

def get_speed():
    return SPEED



