package maze.obj;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Cells {
    WALL("■"),ROAD("　"),START("Ｓ"),GOAL("Ｇ");
    private final String value;
}
