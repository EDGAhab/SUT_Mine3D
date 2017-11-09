package cos.premy.mines.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;

import cos.premy.mines.GameStatus;
import cos.premy.mines.LevelSwitchListener;
import cos.premy.mines.data.Mine;
import cos.premy.mines.data.MineStatus;
import cos.premy.mines.graphics.animations.Line;
import cos.premy.mines.graphics.animations.LineAnimation;
import cos.premy.mines.graphics.animations.LinearLineAnimation;
import cos.premy.mines.graphics.animations.Point;

/**
 * Created by premy on 07.11.2017.
 */

public class MineField implements IDrawable {
    private final Mine data;
    private final GameStatus gameStatus;
    private MineField twin;

    private Line[] crossLines;
    private Line[] minesLines;
    private LineAnimation[] crossLinesAnimation;
    private LineAnimation[] minesLinesAnimation;

    private int x;
    private int y;
    private int width;
    private int height;
    private final int level;

    private static final int ANIMATION_DURATION = 100;

    private Point leftTopCorner = null;
    private Point rightTopCorner = null;
    private Point leftBottomCorner = null;
    private Point rightBottomCorner = null;

    private Paint paintLine;

    public MineField(Mine data, GameStatus status, final int level){
        this.data = data;
        this.gameStatus = status;
        this.level = level;

        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setARGB(255,255,255,255);
        paintLine.setStrokeWidth(3F);

        status.addLevelSwitchListener(new LevelSwitchListener() {
            @Override
            public void levelSwitched(GameStatus status) {
                if(status.getLevel() == level){
                    downloadAnimationStartsFromTwin();
                    refreshAnimations();
                }
            }
        });
    }

    private void initLines(){
        crossLines = new Line[2];
        minesLines = new Line[5];
        refreshLines();
    }

    private void refreshLines(){
        switch (data.getStatus()){
            case BLOCKED:
                crossLines[0] = new Line(leftTopCorner, rightBottomCorner);
                crossLines[1] = new Line(rightTopCorner, leftBottomCorner);
                for(int i = 0; i != 5; i++){
                    int xL = x + (width*(i+1))/(6);
                    Point top = new Point(xL, y);
                    Point bottom = new Point(xL, y + height);
                    minesLines[i] = new Line(top, bottom);
                }
                break;
            case OPENED:
                crossLines[0] = new Line(leftTopCorner, rightTopCorner);
                crossLines[1] = new Line(leftBottomCorner, rightBottomCorner);
                for(int i = 0; i != data.getNeighbors(); i++){
                    int xL = x + (width*(i+1))/(data.getNeighbors() + 1);
                    Point top = new Point(xL, y);
                    Point bottom = new Point(xL, y + height);
                    minesLines[i] = new Line(top, bottom);
                }
                for(int i = data.getNeighbors(); i != 5; i++){
                    minesLines[i] = new Line(leftTopCorner, leftBottomCorner);
                }
                break;
            case UNBLOCKED:
                crossLines[0] = new Line(leftTopCorner, rightBottomCorner);
                crossLines[1] = new Line(rightTopCorner, leftBottomCorner);
                for(int i = 0; i != 5; i++){
                    minesLines[i] = new Line(leftTopCorner, leftBottomCorner);
                }
                break;
        }
    }

    private void initAnimations(){
        crossLinesAnimation = new LineAnimation[2];
        minesLinesAnimation = new LineAnimation[5];
        for(int i = 0; i != 2; i++){
            crossLinesAnimation[i] = new LinearLineAnimation(crossLines[i], crossLines[i], ANIMATION_DURATION);
        }
        for(int i = 0; i != 5; i++){
            minesLinesAnimation[i] = new LinearLineAnimation(minesLines[i], minesLines[i], ANIMATION_DURATION);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for(int i = 0; i != 2; i++){
            Line line = crossLinesAnimation[i].getLine();
            canvas.drawLine(line.start.X, line.start.Y, line.end.X, line.end.Y, paintLine);
        }
        for(int i = 0; i != 5; i++){
            Line line = minesLinesAnimation[i].getLine();
            canvas.drawLine(line.start.X, line.start.Y, line.end.X, line.end.Y, paintLine);
        }
    }

    @Override
    public void setPosition(int x, int width, int y, int height) {
        this.x = x;
        this.width = width;
        this.y = y;
        this.height = height;

        leftTopCorner = new Point(x, y);
        rightTopCorner = new Point(x + width, y);
        leftBottomCorner = new Point(x, y + height);
        rightBottomCorner = new Point(x + width, y + height);

        initLines();
        initAnimations();
    }

    @Override
    public void sendTap(int x, int y) {

    }

    @Override
    public void sendLongTap(int x, int y) {
        if(this.x <= x && this.width + this.x >= x && this.y <= y && this.height + this.y >= y) {
            switch (data.getStatus()) {
                case UNBLOCKED:
                    data.setGameStatus(MineStatus.BLOCKED, gameStatus);
                    break;
                case BLOCKED:
                    data.setGameStatus(MineStatus.UNBLOCKED, gameStatus);
                    break;
            }
            refreshLines();
            refreshAnimations();
        }
    }

    @Override
    public void sendDoubleTap(int x, int y) {
        if(this.x <= x && this.width + this.x >= x && this.y <= y && this.height + this.y >= y) {
            switch (data.getStatus()) {
                case UNBLOCKED:
                    data.setGameStatus(MineStatus.OPENED, gameStatus);
                    refreshLines();
                    refreshAnimations();
                    break;
            }
        }
    }

    public void setTwin(MineField twin){
        this.twin = twin;
    }

    private void refreshAnimations(){
        for(int i = 0; i != 2; i++){
            crossLinesAnimation[i].moveTo(crossLines[i], ANIMATION_DURATION);
        }

        for(int i = 0; i != 5; i++){
            minesLinesAnimation[i].moveTo(minesLines[i], ANIMATION_DURATION);
        }
    }

    private void downloadAnimationStartsFromTwin(){
        for(int i = 0; i != 2; i++){
            crossLinesAnimation[i].setStartLine(twin.getCrossLinesAnimation()[i].getLine());
            crossLinesAnimation[i].startAnimation();
        }

        for(int i = 0; i != 5; i++){
            minesLinesAnimation[i].setStartLine(twin.getMinesLinesAnimation()[i].getLine());
            minesLinesAnimation[i].startAnimation();
        }
    }

    public Line[] getCrossLines(){
        return crossLines;
    }

    public Line[] getMinesLines(){
        return minesLines;
    }

    public LineAnimation[] getCrossLinesAnimation(){
        return crossLinesAnimation;
    }

    public LineAnimation[] getMinesLinesAnimation(){
        return minesLinesAnimation;
    }


}
