package com.nik.foodorder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nik.foodorder.Adapters.MainAdapter;
import com.nik.foodorder.Models.MainModel;
import com.nik.foodorder.databinding.FragmentNonvegBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NonvegFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NonvegFrag extends Fragment {

    FragmentNonvegBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NonvegFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NonvegFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static NonvegFrag newInstance(String param1, String param2) {
        NonvegFrag fragment = new NonvegFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNonvegBinding.inflate(getLayoutInflater());

        View view =binding.getRoot();
        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.burger,"Burger","150","This is a big patty big Burger ..."));
        list.add(new MainModel(R.drawable.fries,"French Fries","80","Very Very Tasty yummy Fries ..."));
        list.add(new MainModel(R.drawable.pizza,"Pizza","250","An Awesome Pizza on the House full pArtyyy pizzaa party ..."));
        list.add(new MainModel(R.drawable.burgertwo,"Big Burger","200","This is a big patty big Burger ..."));
        list.add(new MainModel(R.drawable.burger,"Burger","140","This is a big patty big Burger ..."));
        list.add(new MainModel(R.drawable.burger,"Burger","40","This is a big patty big Burger ..."));

        MainAdapter adapter = new MainAdapter(list,this.getContext());
        binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        binding.recyclerview.setLayoutManager(layoutManager);

        return view;
    }
}