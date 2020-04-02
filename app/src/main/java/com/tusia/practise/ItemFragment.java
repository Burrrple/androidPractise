package com.tusia.practise;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.tusia.practise.model.Item;
import com.tusia.practise.service.ItemService;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private ItemService itemService;
    private Button addBtn;
    private MyItemRecyclerViewAdapter adapter;
    private List<Item> items;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        itemService = new ItemService(((PractiseApp)getActivity().getApplication()).getDaoSession());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        View recycler = view.findViewById(R.id.list);


        // Set the adapter
            if (recycler instanceof RecyclerView) {
            Context context = recycler.getContext();
            RecyclerView recyclerView = (RecyclerView) recycler;

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            items = itemService.getItems();
            adapter = new MyItemRecyclerViewAdapter(items, new OnListFragmentInteractionListener() {
                @Override
                public void onListFragmentInteraction(Item item) {
                    item.setDescription("Changed");
                    itemService.saveItem(item);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onDeleteClick(Item item) {
                    items.remove(item);
                    itemService.deleteItem(item);
                    adapter.notifyDataSetChanged();
                }
            });
            recyclerView.setAdapter(adapter);

        }
        addBtn = view.findViewById(R.id.add_btn);
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Item item = new Item();
                    item.setName("New Item");
                    item.setDescription("New Description");

                    itemService.saveItem(item);
                    items.add(item);

//                    adapter.notifyItemInserted((int)itemService.countItems()-1);
                    adapter.notifyDataSetChanged();
                }
            });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Item item);
        void onDeleteClick(Item item);
    }
}
