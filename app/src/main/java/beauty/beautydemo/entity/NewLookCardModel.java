package beauty.beautydemo.entity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.andtinder.model.CardModel;

/**
 * Created by LJW on 15/7/9.
 */
public class NewLookCardModel extends CardModel {

    public String image;

    public NewLookCardModel() {
    }

    public NewLookCardModel(String title, String description, Drawable cardImage) {
        super(title, description, cardImage);
    }

    public NewLookCardModel(String title, String description, Bitmap cardImage) {
        super(title, description, cardImage);
    }
}
