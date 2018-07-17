package com.example.geo.mybeobachter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.util.HashMap;

/**
 * Creates a new instance of a Carcassonne Board.
 *
 */
public class Board extends View {

    Context context;

    int display_height;
    int display_width;

    int board_height;
    int board_width;

    int field_height;
    int field_width;

    int simple_width;

    int tile_size = 25;

    HashMap<Integer, Integer> playerMeeple;

    static int[][][] tiles;

    static int[] tileID = {
            R.drawable.tile_0,
            R.drawable.tile_1,
            R.drawable.tile_2,
            R.drawable.tile_3,
            R.drawable.tile_4,
            R.drawable.tile_5,
            R.drawable.tile_6,
            R.drawable.tile_7,
            R.drawable.tile_8,
            R.drawable.tile_9,
            R.drawable.tile_10,
            R.drawable.tile_11,
            R.drawable.tile_12,
            R.drawable.tile_13,
            R.drawable.tile_14,
            R.drawable.tile_15,
            R.drawable.tile_16,
            R.drawable.tile_17,
            R.drawable.tile_18,
            R.drawable.tile_19,
            R.drawable.tile_20,
            R.drawable.tile_21,
            R.drawable.tile_22,
            R.drawable.tile_23
    };

    static int[] meepleID = {
            R.drawable.meeple_red,
            R.drawable.meeple_blue,
            R.drawable.meeple_green,
            R.drawable.meeple_black,
            R.drawable.meeple_lila,
            R.drawable.meeple_lightblue,
            R.drawable.meeple_yellow
    };

    /**
     * Standard Getter/Setter to board_height, board_width and tiles.
     *
     */
    public int getBoard_height() {
        return board_height;
    }

    public void setBoard_height(int board_height) {
        this.board_height = board_height;
    }

    public int getBoard_width() {
        return board_width;
    }

    public void setBoard_width(int board_width) {
        this.board_width = board_width;
    }

    public static int[][][] getTiles() {
        return tiles;
    }

    public static void setTiles(int[][][] tiles) {
        Board.tiles = tiles;
    }

    /**
     * Board Constructor.
     *
     * @param context
     *                  The app´s context
     * @param nr_of_tiles_height
     *                  The height of the resulting board
     * @param nr_of_tiles_width
     *                  The width of the resulting board
     * @param playerMeeple
     *                  Hashmap containing the players of the match
     */
    public Board(Context context, int nr_of_tiles_height, int nr_of_tiles_width, HashMap<Integer, Integer> playerMeeple) {
        super(context);

        Log.i("msg", "inside Board Constructor");
        board_height = nr_of_tiles_height;
        board_width  = nr_of_tiles_width;
        this.playerMeeple = playerMeeple;

        tiles = new int[board_width][board_height][4];

        for (int h = 0; h < board_height; h++) {
            for (int w = 0; w < board_width; w++) {
                tiles[w][h][0] = -1;
            }
        }

        init(context);
    }

    /**
     * Finds the correct sizes for the tiles by accessing device properties via context.
     *
     * @param cont
     *              The app´s context
     */
    private void init(Context cont) {
        Log.i("msg", "inside init");
        context = cont;

        display_height = context.getResources().getDisplayMetrics().heightPixels;
        display_width  = context.getResources().getDisplayMetrics().widthPixels;

        field_height = display_width / board_height;
        field_width  = display_width / board_width;
        tile_size    = field_width;

        Log.i("display_height", String.valueOf(display_height));
        Log.i("display_width", String.valueOf(display_width));
        Log.i("board_height", String.valueOf(board_height));
        Log.i("board_width", String.valueOf(board_width));

        Log.i("field_width", String.valueOf(field_width));
        Log.i("tile_size", String.valueOf(tile_size));
    }

    /**
     * Adds a tile to the list tiles.
     *
     * @param tileID
     *              the tile´s ID (0-23)
     * @param x
     * @param y
     *              x and y coordinate where the tile is put
     * @param rotation
     *              rotation of the tile (0-3)
     * @param position
     *              position of the Meeple on the tile (0-12)
     * @param meepleOwner
     *              The player whose Meeple is added to the tile, needed for the color.
     * @return true
     */
    public boolean addTile(int tileID, int x, int y, int rotation, int position, int meepleOwner) {
        tiles[x][y][0] = tileID;
        tiles[x][y][1] = rotation;
        tiles[x][y][2] = position;
        tiles[x][y][3] = meepleOwner;
        Log.i("msg", "adding a tile, tileID: " + tileID);

        return true;
    }

    /**
     * To generate the tiles representation with a Meeple, bitmaps are to be created.
     * A Matrix is created to change the bitmap´s size and then drawn on the canvas.
     * @param tileID
     *              the tile´s ID (0-23)
     * @param x
     * @param y
     *              x and y coordinate where the tile is put
     * @param rotation
     *              rotation of the tile (0-3)
     * @param position
     *              position of the Meeple on the tile (-1 to 12)
     * @param meepleOwner
     *              The player whose Meeple is added to the tile, needed for the color.
     * @return true
     */
    private boolean generateTile(int tileID, int rotation, Canvas canvas, int x, int y,int position, int meepleOwner) {
        Log.i("msg", "generating a tile " + tileID);
        Bitmap field = generateBitmap(tileID, rotation, tile_size, tile_size, position, meepleOwner);

        Matrix matrix = new Matrix();
        matrix.setTranslate(x * tile_size, y * tile_size);

        canvas.drawBitmap(field, matrix, null);
        return true;
    }

    /**
     * In case there is the need for an empty field (not anymore)
     * @param fieldID
     * @param rotation
     * @param canvas
     * @param x
     * @param y
     * @return
     */
    private boolean generateEmptyField(int fieldID, int rotation, Canvas canvas, int x, int y) {
        Bitmap field;
        field = generateBitmap(fieldID, rotation, field_width, field_height,-1,0);
        Matrix matrix = new Matrix();
        matrix.setTranslate(x * simple_width + tile_size, y * simple_width + tile_size);
        Log.i("msg", "adding an empty field");
        canvas.drawBitmap(field, matrix, null);
        return true;
    }

    /**
     * This functions loads the assets and generates a bitmap and rotates it, if needed.
     * If a Meeple is placed, it will be added to the bitmap.
     *
     * @param fieldID
     *              The tile´s ID (0-23)
     * @param rotation
     *              Rotation of the tile (0-3)
     * @param width
     * @param height
     *              Originally from the context, the bitmap´s sizes according to the device
     * @param position
     *              Meeple´s position (-1 to 12)
     * @param meepleOwner
     *              Meeple´s owner (color)
     * @return bitmap
     */
    private Bitmap generateBitmap(int fieldID, int rotation, int width, int height, int position, int meepleOwner) {
        Log.i("msg", "generating a bitmap");

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), fieldID);

        Matrix matrix = new Matrix();

        switch (rotation) {
            default:
            case 0:
                break;
            case 1:
                matrix.postRotate(90);
                break;
            case 2:
                matrix.postRotate(180);
                break;
            case 3:
                matrix.postRotate(270);
                break;
        }

        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        // bitmap = resizeBitmap(bitmap,tile_size,tile_size);
        bitmap = Bitmap.createScaledBitmap(bitmap, tile_size*10, tile_size*10, false);

        if(position!=-1) {
            Bitmap bitmapMeeple = BitmapFactory.decodeResource(context.getResources(), meepleID[playerMeeple.get(meepleOwner)]);
            bitmap = meepleOverlay(bitmap, resizeBitmap(bitmapMeeple, field_width / 5, field_width / 5), position);
        }
        return bitmap;
    }

    /**
     * This function puts the Meeple on the map.
     *
     * @param bitmap1
     *              The bitmap showing the tile.
     * @param bitmap2
     *              Bitmap with meeple texture.
     * @param meeplePosition
     *              To correctly display meeple´s placement on tiles, the position is needed.
     * @return
     *              Resulting bitmap which has already been drawn on the canvas.
     */
    public static Bitmap meepleOverlay(Bitmap bitmap1, Bitmap bitmap2,int meeplePosition) {
        int bitmap1Width = bitmap1.getWidth();
        int bitmap1Height = bitmap1.getHeight();
        int bitmap2Width = bitmap2.getWidth();
        int bitmap2Height = bitmap2.getHeight();

        Log.d("bitmap1Width",bitmap1.getWidth()+"");
        Log.d("bitmap1height",bitmap1.getHeight()+"");


        float marginLeft;
        float marginTop;

        switch(meeplePosition){
            case 11:
                marginLeft = (float) (bitmap1Width*0.10 - bitmap2Width*0.10);
                Log.d("marginLeft",marginLeft+"");
                marginTop = (float) (bitmap1Height * 0.05 - bitmap2Height * 0.05);
                Log.d("marginTop",marginTop+"");
                break;
            case 0:
                marginLeft = (float) (bitmap1Width*0.025 - bitmap2Width*0.025);
                Log.d("marginLeft",marginLeft+"");
                marginTop = (float) (bitmap1Height * 0.10 - bitmap2Height * 0.10);
                Log.d("marginTop",marginTop+"");
                break;
            case 8:
                marginLeft = (float) (bitmap1Width*0.10 - bitmap2Width*0.10);
                Log.d("marginLeft",marginLeft+"");
                marginTop = (float) (bitmap1Height * 0.95 - bitmap2Height * 0.95);
                Log.d("marginTop",marginTop+"");
                break;
            case 9:
                marginLeft = (float) (bitmap1Width*0.025 - bitmap2Width*0.025);
                Log.d("marginLeft",marginLeft+"");
                marginTop = (float) (bitmap1Height * 0.95 - bitmap2Height * 0.95);
                Log.d("marginTop",marginTop+"");
                break;
            case 2:
                marginLeft = (float) (bitmap1Width*0.90 - bitmap2Width*0.90);
                Log.d("marginLeft",marginLeft+"");
                marginTop = (float) (bitmap1Height * 0.05 - bitmap2Height * 0.05);
                Log.d("marginTop",marginTop+"");
                break;
            case 3:
                marginLeft = (float) (bitmap1Width*0.975 - bitmap2Width*0.975);
                Log.d("marginLeft",marginLeft+"");
                marginTop = (float) (bitmap1Height * 0.10 - bitmap2Height * 0.10);
                Log.d("marginTop",marginTop+"");
                break;
            case 5:
                marginLeft = (float) (bitmap1Width*0.975 - bitmap2Width*0.90);
                Log.d("marginLeft",marginLeft+"");
                marginTop = (float) (bitmap1Height * 0.95 - bitmap2Height * 0.95);
                Log.d("marginTop",marginTop+"");
                break;
            case 6  :
                marginLeft = (float) (bitmap1Width*0.90 - bitmap2Width*0.90);
                Log.d("marginLeft",marginLeft+"");
                marginTop = (float) (bitmap1Height * 0.95 - bitmap2Height * 0.95);
                Log.d("marginTop",marginTop+"");
                break;
            case 12:
                marginLeft = (float) (bitmap1Width * 0.5 - bitmap2Width * 0.5);
                Log.d("marginLeft",marginLeft+"");
                marginTop = (float) (bitmap1Height * 0.5 - bitmap2Height * 0.5);
                Log.d("marginTop",marginTop+"");
                break;
            case 1:
                marginLeft = (float) (bitmap1Width * 0.5 - bitmap2Width * 0.5);
                Log.d("marginLeft",marginLeft+"");
                marginTop = (float) (bitmap1Height * 0.05 - bitmap2Height * 0.05);
                Log.d("marginTop",marginTop+"");
                break;
            case 7:
                marginLeft = (float) (bitmap1Width * 0.5 - bitmap2Width * 0.5);
                Log.d("marginLeft",marginLeft+"");
                marginTop = (float) (bitmap1Height * 0.95 - bitmap2Height * 0.95);
                Log.d("marginTop",marginTop+"");
                break;
            case 10:
                marginLeft = (float) (bitmap1Width*0.05 - bitmap2Width*0.05);
                Log.d("marginLeft",marginLeft+"");
                marginTop = (float) (bitmap1Height * 0.5 - bitmap2Height * 0.5);
                Log.d("marginTop",marginTop+"");
                break;
            case 4:
                marginLeft = (float) (bitmap1Width*0.95 - bitmap2Width*0.95);
                Log.d("marginLeft",marginLeft+"");
                marginTop = (float) (bitmap1Height * 0.5 - bitmap2Height * 0.5);
                Log.d("marginTop",marginTop+"");
                break;
            default:
                //defaultposition of Meeple
                marginLeft = (float) (bitmap1Width * 0.5 - bitmap2Width * 0.5);
                Log.d("marginLeft2",marginLeft+"");
                marginTop = (float) (bitmap1Height * 0.5 - bitmap2Height * 0.5);
                Log.d("marginTop2",marginTop+"");
        }

        Bitmap overlayBitmap = Bitmap.createBitmap(bitmap1Width, bitmap1Height, bitmap1.getConfig());
        Canvas canvas = new Canvas(overlayBitmap);
        canvas.drawBitmap(bitmap1, new Matrix(), null);
        canvas.drawBitmap(bitmap2, marginLeft, marginTop, null);
        return overlayBitmap;
    }

    /**
     * Used in generateBitmap(...) if Meeple are present on a tile and to rescale the
     * Meeple according to the general size.
     *
     * @param bm
     *          The bitmap to be resized.
     * @param newWidth
     * @param newHeight
     *          New width and height for the bitmap.
     * @return
     *          The resized bitmap.
     */
    public Bitmap resizeBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();

        return resizedBitmap;
    }

    /**
     * Overridden method onDraw. Uses generateTile(...) to fill the board and then draw each
     * tile which has already been laid out.
     *
     * @param canvas
     *              The canvas used to draw bitmaps on.
     */
    protected void onDraw(Canvas canvas) {
        Log.i("msg", "in onDraw");

        for (int h = 0; h < board_height; h++) {
            for (int w = 0; w < board_width; w++) {
                if (tiles[w][h][0] >= 0 && tiles[w][h][0] <= 34) {
                    generateTile(tileID[tiles[w][h][0]], tiles[w][h][1], canvas, w, h,tiles[w][h][2],tiles[w][h][3]);
                }
            }
        }

        Log.i("msg", "finished onDraw");
    }
}