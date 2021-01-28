package diiage.potherat.demo.demoapp3.ui.starwars;

import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import diiage.potherat.demo.demoapp3.R;
import diiage.potherat.demo.demoapp3.databinding.FragmentStarwarsBinding;
import diiage.potherat.demo.demoapp3.model.sw.People;
import diiage.potherat.demo.demoapp3.ui.gallery.GalleryViewModel;

@AndroidEntryPoint
public class StarwarsFragment extends Fragment {

    @Inject
    public StarwarsViewModel starwarsViewModel;

    public FragmentStarwarsBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStarwarsBinding.inflate(inflater, container, false);
        ready();
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText yourEditText = (EditText) view.findViewById(R.id.myEditText);

        yourEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                Log.d("dtring", str);
                starwarsViewModel.onEditInput(str);
            }
        });
    }


    private void ready() {
        if ( binding != null && starwarsViewModel != null ) {
            binding.setLifecycleOwner(this);
            binding.setViewModel(starwarsViewModel);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        starwarsViewModel.getOutput().observe(getViewLifecycleOwner(),people -> {
            if (people != null) {
                binding.outputholder.setText(people);
            }
        });

    }
}