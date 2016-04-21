package com.sir.app.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.sir.app.R;
import com.sir.app.base.BaseFragmentV4;
import com.sir.app.base.tools.ToolAlert;
import com.sir.app.material.app.DatePickerDialog;
import com.sir.app.material.app.Dialog;
import com.sir.app.material.app.SimpleDialog;
import com.sir.app.material.app.ThemeManager;
import com.sir.app.material.app.TimePickerDialog;
import com.sir.app.material.widget.EditText;
import com.sir.app.material.app.DialogFragment;

import java.text.SimpleDateFormat;

/**
 * Widget Dialog
 * Created by zhuyinan on 2015/12/31.
 */
public class DialogsFragment extends BaseFragmentV4 implements View.OnClickListener {

    @Override
    public int bindLayout() {
        return R.layout.fragment_dialog;
    }

    @Override
    public void initView(View view) {
        view.findViewById(R.id.btn_1).setOnClickListener(this);
        view.findViewById(R.id.btn_2).setOnClickListener(this);
        view.findViewById(R.id.btn_3).setOnClickListener(this);
        view.findViewById(R.id.btn_4).setOnClickListener(this);
        view.findViewById(R.id.btn_5).setOnClickListener(this);
        view.findViewById(R.id.btn_6).setOnClickListener(this);
        view.findViewById(R.id.btn_7).setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void onClick(View view) {
        Dialog.Builder builder = null;
        boolean isLightTheme = ThemeManager.getInstance().getCurrentTheme() == 0;
        switch (view.getId()) {
            case R.id.btn_1:
                builder = new SimpleDialog.Builder(isLightTheme ? R.style.SimpleDialogLight : R.style.SimpleDialog) {
                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        ToolAlert.showShort("Agreed");
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        ToolAlert.showShort("Disagreed");
                        super.onNegativeActionClicked(fragment);
                    }
                };

                ((SimpleDialog.Builder) builder).message("Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.")
                        .title("Use Google's location service?")
                        .positiveAction("AGREE")
                        .negativeAction("DISAGREE");
                break;
            case R.id.btn_2:
                builder = new SimpleDialog.Builder(isLightTheme ? R.style.SimpleDialogLight : R.style.SimpleDialog) {

                    @Override
                    protected void onBuildDone(Dialog dialog) {
                        dialog.layoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    }

                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        EditText et_pass = (EditText) fragment.getDialog().findViewById(R.id.custom_et_password);
                        ToolAlert.showShort("Connected. pass=" + et_pass.getText().toString());
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        ToolAlert.showShort("Cancelled");
                        super.onNegativeActionClicked(fragment);
                    }
                };

                builder.title("Google Wi-Fi")
                        .positiveAction("CONNECT")
                        .negativeAction("CANCEL")
                        .contentView(R.layout.layout_dialog_custom);
                break;
            case R.id.btn_3:
                builder = new SimpleDialog.Builder(isLightTheme ? R.style.SimpleDialogLight : R.style.SimpleDialog) {
                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        ToolAlert.showShort("You have selected " + getSelectedValue() + " as phone ringtone.");
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        ToolAlert.showShort("Cancelled");
                        super.onNegativeActionClicked(fragment);
                    }
                };

                ((SimpleDialog.Builder) builder).items(new String[]{"None", "Callisto", "Dione", "Ganymede", "Hangouts Call", "Luna", "Oberon", "Phobos"}, 0)
                        .title("Phone Ringtone")
                        .positiveAction("OK")
                        .negativeAction("CANCEL");
                break;
            case R.id.btn_4:
                builder = new SimpleDialog.Builder(isLightTheme ? R.style.SimpleDialogLight : R.style.SimpleDialog) {
                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        CharSequence[] values = getSelectedValues();
                        if (values == null)
                            ToolAlert.showShort("You have selected nothing.");
                        else {
                            StringBuffer sb = new StringBuffer();
                            sb.append("You have selected ");
                            for (int i = 0; i < values.length; i++)
                                sb.append(values[i]).append(i == values.length - 1 ? "." : ", ");
                            ToolAlert.showShort(sb.toString());
                        }
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        ToolAlert.showShort("Cancelled");
                        super.onNegativeActionClicked(fragment);
                    }
                };

                ((SimpleDialog.Builder) builder).multiChoiceItems(new String[]{"Soup", "Pizza", "Hotdogs", "Hamburguer", "Coffee", "Juice", "Milk", "Water"}, 2, 5)
                        .title("Food Order")
                        .positiveAction("OK")
                        .negativeAction("CANCEL");
                break;
            case R.id.btn_5:
                builder = new TimePickerDialog.Builder(isLightTheme ? R.style.Material_App_Dialog_TimePicker_Light : R.style.Material_App_Dialog_TimePicker, 24, 00) {
                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        TimePickerDialog dialog = (TimePickerDialog) fragment.getDialog();
                        ToolAlert.showShort("Time is " + dialog.getFormattedTime(SimpleDateFormat.getTimeInstance()));
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        ToolAlert.showShort("Cancelled");
                        super.onNegativeActionClicked(fragment);
                    }
                };

                builder.positiveAction("OK")
                        .negativeAction("CANCEL");
                break;
            case R.id.btn_6:
                builder = new DatePickerDialog.Builder(isLightTheme ? R.style.Material_App_Dialog_DatePicker_Light : R.style.Material_App_Dialog_DatePicker) {
                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        DatePickerDialog dialog = (DatePickerDialog) fragment.getDialog();
                        String date = dialog.getFormattedDate(SimpleDateFormat.getDateInstance());
                        ToolAlert.showShort("Date is " + date);
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        ToolAlert.showShort("Cancelled");
                        super.onNegativeActionClicked(fragment);
                    }
                };
                builder.positiveAction("OK")
                        .negativeAction("CANCEL");
                break;
        }
        DialogFragment fragment = DialogFragment.newInstance(builder);
        fragment.show(getFragmentManager(), null);
    }
}
