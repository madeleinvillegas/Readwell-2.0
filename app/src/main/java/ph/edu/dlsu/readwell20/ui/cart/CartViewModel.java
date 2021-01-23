package ph.edu.dlsu.readwell20.ui.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ph.edu.dlsu.readwell20.Book;
import ph.edu.dlsu.readwell20.MainActivity;

public class CartViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CartViewModel() {
        Book a = MainActivity.cart.peek();
        mText = new MutableLiveData<>();
        mText.setValue(a.title);
    }

    public LiveData<String> getText() {
        return mText;
    }
}