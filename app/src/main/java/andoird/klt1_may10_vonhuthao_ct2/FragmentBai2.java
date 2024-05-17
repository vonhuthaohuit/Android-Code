package andoird.klt1_may10_vonhuthao_ct2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBai2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBai2 extends Fragment {

    ArrayList<QuocGia> quocGias = new ArrayList<>();
    ListView listView;
    AdapterQuocGia adapterQuocGia;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentBai2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBai2.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBai2 newInstance(String param1, String param2) {
        FragmentBai2 fragment = new FragmentBai2();
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
        View view = inflater.inflate(R.layout.fragment_bai2, container, false);
        try {
            ArrayList<String> listChauLuc = new ArrayList<>();
            listChauLuc.add("Tất cả");
            listChauLuc.add("Châu Á");
            listChauLuc.add("Châu Phi");
            listChauLuc.add("Châu Âu");
            listChauLuc.add("Châu Mỹ");
            listChauLuc.add("Châu Đại Dương");


            quocGias = readDSQuocGia("dsquocgia.xml");
            listView = view.findViewById(R.id.listView_QuocGia);
            Button btnRestore = view.findViewById(R.id.btn_restore);
            Button btn_TimKiem = view.findViewById(R.id.btn_Tim);
            EditText edt_TimKiem = view.findViewById(R.id.edt_TimKiem);

            adapterQuocGia = new AdapterQuocGia(getActivity(), R.layout.layout_custom_list_view_quoc_gia ,quocGias);
            listView.setAdapter(adapterQuocGia);


            Spinner spinner = view.findViewById(R.id.spinner_ChauLuc);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, listChauLuc);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedChauLuc = listChauLuc.get(position);
                    ArrayList<QuocGia> listQuocGiaMoi = new ArrayList<>();
                    if(selectedChauLuc.equals("Tất cả")){
                        listQuocGiaMoi = quocGias;
                    }
                    else{
                        for (int i = 0; i < quocGias.size(); i++) {
                            if(selectedChauLuc.equals(quocGias.get(i).chauLuc)){
                                listQuocGiaMoi.add(quocGias.get(i));
                            }
                        }
                    }

                    if (listQuocGiaMoi.isEmpty()) {
                        listQuocGiaMoi.clear();
                        Toast.makeText(getActivity(), "Châu lục bạn chọn không có quốc gia trong dữ liệu!", Toast.LENGTH_SHORT).show();
                    }
                    adapterQuocGia = new AdapterQuocGia(getActivity(), R.layout.layout_custom_list_view_quoc_gia, listQuocGiaMoi);
                    listView.setAdapter(adapterQuocGia);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            btn_TimKiem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<QuocGia> listQuocGiaMoi = new ArrayList<>();
                    String chauLuc = edt_TimKiem.getText().toString();
                    for(int i = 0; i < quocGias.size(); i++){
                        if(chauLuc.equals(quocGias.get(i).chauLuc)){
                            listQuocGiaMoi.add(quocGias.get(i));
                            adapterQuocGia = new AdapterQuocGia(getActivity(), R.layout.layout_custom_list_view_quoc_gia ,listQuocGiaMoi);
                            listView.setAdapter(adapterQuocGia);
                        }
                    }
                    if(listQuocGiaMoi.isEmpty()){
                        adapterQuocGia = new AdapterQuocGia(getActivity(), R.layout.layout_custom_list_view_quoc_gia ,listQuocGiaMoi);
                        listView.setAdapter(adapterQuocGia);
                        Toast.makeText(getActivity(), "Châu lục bạn chọn không có quốc gia trong dữ liệu!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getContext(), ChiTietQuocGia.class);
                    intent.putExtra("hinh", quocGias.get(position).hinh);
                    intent.putExtra("ten", quocGias.get(position).ten);
                    intent.putExtra("dientich", quocGias.get(position).dienTich);
                    intent.putExtra("danso", quocGias.get(position).danSo);
                    intent.putExtra("thudo", quocGias.get(position).thuDo);
                    intent.putExtra("chauluc", quocGias.get(position).chauLuc);
                    startActivity(intent);
                }

            });

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Bạn có chắc muốn xóa quốc gia này?")
                            .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    quocGias.remove(position);
                                    adapterQuocGia.notifyDataSetChanged();
                                    Toast.makeText(getActivity(), "Đã xóa quốc gia", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                    return true;
                }
            });
            btnRestore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        quocGias = readDSQuocGia("dsquocgia.xml");
                        adapterQuocGia = new AdapterQuocGia(getActivity(), R.layout.layout_custom_list_view_quoc_gia ,quocGias);
                        listView.setAdapter(adapterQuocGia);
                        Toast.makeText(getActivity(), "Đã khôi phục dữ liệu ban đầu", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (XmlPullParserException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        }

        return view;
    }
    public ArrayList<QuocGia> readDSQuocGia(String fileName) throws IOException, XmlPullParserException {
        ArrayList<QuocGia> arrayListQuocGia = new ArrayList<>();

        InputStream inputStream = getResources().getAssets().open(fileName);
        XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = xmlPullParserFactory.newPullParser();
        parser.setInput(inputStream, "UTF-8");

        int tagType;
        String nodeName;

        while ((tagType = parser.next()) != XmlPullParser.END_DOCUMENT) {
            switch (tagType) {
                case XmlPullParser.START_TAG:
                    nodeName = parser.getName();
                    if (nodeName.equals("quocgia")) {
                        QuocGia quocGia = new QuocGia();
                        while ((tagType = parser.next()) != XmlPullParser.END_TAG) {
                            if (tagType == XmlPullParser.START_TAG) {
                                nodeName = parser.getName();
                                if (nodeName.equals("hinh")) {
                                    String idHinhAnh = parser.nextText();
                                    int drawableId = getResources().getIdentifier(idHinhAnh, "drawable", getContext().getPackageName());
                                    quocGia.setHinh(drawableId);
                                } else if (nodeName.equals("ten")) {
                                    quocGia.setTen(parser.nextText());
                                } else if (nodeName.equals("dientich")) {
                                    quocGia.setDienTich(parser.nextText());
                                } else if (nodeName.equals("danso")) {
                                    long danSo = Long.parseLong(parser.nextText().replace(".", ""));
                                    quocGia.setDanSo(danSo);
                                } else if (nodeName.equals("thudo")) {
                                    quocGia.setThuDo(parser.nextText());
                                } else if (nodeName.equals("chauluc")) {
                                    quocGia.setChauLuc(parser.nextText());
                                }
                            }
                        }
                        arrayListQuocGia.add(quocGia);
                    }
                    break;
            }
        }
        inputStream.close();

        return arrayListQuocGia;
    }
}