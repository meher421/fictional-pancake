package com.njk.app.testadmin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myapplication.R;
import com.njk.app.utils.Logger;
import com.njk.app.testdto.Market;
import com.njk.app.testdto.Product;
import com.njk.app.testdto.uplink.UplinkData;

import java.util.ArrayList;

public class AdminFragment extends Fragment implements View.OnClickListener, AdminActivity.OnClickAdapterItem {

    public static final String TAG = "AdminFragment-123456";
    int productId = -1;
    private RecyclerView mRecyclerView;
    private AdminEditAdapter mAdapter;
    private Product mProduct = null;
    private EditText mEditText;
    private TextView mTextView;
    private RelativeLayout mRelativeLayout;
    private ImageView mSubmit;


    public static AdminFragment init(int val) {
        AdminFragment fragment = new AdminFragment();

        Bundle args = new Bundle();
        args.putInt("val", val);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        productId = getArguments() != null ? getArguments().getInt("val") : -1;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Logger.i(TAG, "oncreateview");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin, container, false);

        mEditText = (EditText) view.findViewById(R.id.et_product_name);
//        mTextView = (TextView) view.findViewById(R.id.product_name);
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.edit_layout);
        mSubmit = (ImageView) view.findViewById(R.id.product_item_action);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSubmit.setOnClickListener(this);

        if (productId != -1) {
            mProduct = UplinkData.getInstance().getProduct(productId);
        } else {
            mProduct = new Product(-1, "", new ArrayList<Market>());
        }

        mAdapter = new AdminEditAdapter(mProduct.getMarkets(), getActivity(), this);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }


    /**
     * onClick method for the views in the fragment
     *
     * @param v
     */
    @Override
    public void onClick(View v) {


    }


    /**
     * Click listener of adapter item views
     *
     * @param view
     * @param position
     */
    @Override
    public void onClickAdapterItem(View view, int position) {

        if (view.getId() == R.id.item_action) {

            Logger.i(TAG, "onCLick : " + position);
            if (mAdapter.getItemViewType(position) == AdminEditAdapter.WRITE_MODE) {
                Market item = mAdapter.getItem(position);
//                mAdapter.notifyItemChanged(position);
                Logger.i(TAG, "onCLick price : " + item.getPrice());
            }

        } else if (view.getId() == R.id.add_market) {
            Logger.i(TAG, "onCLick add_market: " + position);
            mAdapter.addItem(new Market(-1));

            if (mEditText.getText().toString().length() == 0) {
                Toast.makeText(getContext(), " product name error", Toast.LENGTH_LONG).show();
            }
        }
    }

    public Product submitProduct() {


        if (mEditText.getText().toString().length() == 0) {
            Toast.makeText(getContext(), " product name error", Toast.LENGTH_LONG).show();
            return null;
        }

        ArrayList<Market> markets = mAdapter.getList();
        if (markets.get(0).getId() == -1) {
            Toast.makeText(getContext(), "no markets", Toast.LENGTH_LONG).show();
            return null;
        }

        mProduct.setName(mEditText.getText().toString());
        mProduct.setMarkets(markets);
        return mProduct;

    }
}
