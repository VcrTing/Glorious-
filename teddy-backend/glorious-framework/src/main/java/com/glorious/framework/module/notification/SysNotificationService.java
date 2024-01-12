package com.glorious.framework.module.notification;

import com.glorious.common.define.mail.MailConstants;
import com.glorious.common.define.mail.MailStyleConstants;
import com.glorious.common.define.mail.MailSubjectConstants;
import com.glorious.common.properties.GloriousMailProperty;
import com.glorious.framework.component.tools.MailTool;
import com.glorious.model.entity.restock.Invoice;
import com.glorious.utils.utils.basic.QDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysNotificationService {

    @Autowired
    MailTool mailTool;

    @Autowired
    GloriousMailProperty property;

    /**
     * 通知 系统人员，发票入货成功
     */
    public void notifyInvoiceInsert(Invoice invoice) {
        StringBuilder sb = new StringBuilder();
        String s = "<div class=\"page\">\n" +
                "        <div class=\"banner\">\n" +
                "            <div class=\"fx-l\">\n" +
                "                <img class=\"img\" src=\"https://img2.baidu.com/it/u=516166943,3974957162&fm=253&fmt=auto&app=138&f=JPEG?w=324&h=324\"/>\n" +
                "                <h2 class=\"success pl\">入貨成功</h2>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <div class=\"item\">\n" +
                "                <p>狀態：</p>\n" +
                "                <div class=\"status success\">成功</div>\n" +
                "            </div>\n" +
                "            <div class=\"item\">\n" +
                "                <p>發票編號：</p>\n" +
                "                <div class=\"status success\">" + invoice.getInvoice_id() + "</div>\n" +
                "            </div>\n" +
                "            <div class=\"item\">\n" +
                "                <p>入貨金額：</p>\n" +
                "                <div class=\"price\">" + invoice.getTotal_price() + "</div>\n" +
                "            </div>\n" +
                "            <div class=\"item\">\n" +
                "                <p>入貨時間：</p>\n" +
                "                <div class=\"time\">" + QDateUtil.now(true) + "</div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>";
        sb.append(MailConstants.PREFIX)
                .append(s)
                .append(MailStyleConstants.SUCCESS)
                .append(MailConstants.SUFFIX);

        mailTool.sendHtml(property.getRecipient(), MailSubjectConstants.INVOICE_SUCCESS, sb.toString());
    }

}
