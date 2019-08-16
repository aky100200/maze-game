package maze.obj;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Cells {
    WALL("■"),ROAD("　"),START("Ｓ"),GOAL("Ｇ");
    private final String value;
}
