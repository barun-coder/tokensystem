
package com.displayfort.dftoken.ui.feedback;

import com.displayfort.dftoken.data.model.api.response.TokenResponse;


public class SuggestionItemViewModel {


    private final TokenResponse.TokenDao genericDao;

    private SuggestionItemViewModelListener mResponseListener;

    public SuggestionItemViewModel(TokenResponse.TokenDao generic, SuggestionItemViewModelListener listener) {
        this.mResponseListener = listener;
        this.genericDao = generic;
    }

    public void onObjAdd() {
        mResponseListener.onObjAdd(genericDao);
    }

    public interface SuggestionItemViewModelListener {

        void onObjAdd(TokenResponse.TokenDao generic);
    }
}
