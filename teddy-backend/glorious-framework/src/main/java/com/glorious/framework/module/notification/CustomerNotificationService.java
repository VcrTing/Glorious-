package com.glorious.framework.module.notification;

import com.glorious.common.define.mail.MailConstants;
import com.glorious.common.define.mail.MailStyleConstants;
import com.glorious.common.define.mail.MailSubjectConstants;
import com.glorious.common.properties.GloriousMailProperty;
import com.glorious.framework.component.tools.MailTool;
import com.glorious.model.entity.order.Order;
import com.glorious.model.entity.order.RefundedRecord;
import com.glorious.utils.utils.basic.QDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerNotificationService {

    @Autowired
    MailTool mailTool;

    @Autowired
    GloriousMailProperty property;

    /**
    * 告诉客户，下单成功
    */
    public void notifyCheckoutSuccess(Order order) {
        StringBuilder sb = new StringBuilder();
        String s = "<div class=\"page\">\n" +
                "        <div class=\"banner\">\n" +
                "            <div class=\"fx-l\">\n" +
                "                <img class=\"img\" src=\"https://img2.baidu.com/it/u=516166943,3974957162&fm=253&fmt=auto&app=138&f=JPEG?w=324&h=324\"/>\n" +
                "                <h2 class=\"success pl\">下單成功</h2>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <div class=\"item\">\n" +
                "                <p>狀態：</p>\n" +
                "                <div class=\"status success\">成功</div>\n" +
                "            </div>\n" +
                "            <div class=\"item\">\n" +
                "                <p>訂單編號：</p>\n" +
                "                <div class=\"status success\">" + order.getOrder_id() + "</div>\n" +
                "            </div>\n" +
                "            <div class=\"item\">\n" +
                "                <p>訂單金額：</p>\n" +
                "                <div class=\"price\">" + order.getTotal_price() + "</div>\n" +
                "            </div>\n" +
                "            <div class=\"item\">\n" +
                "                <p>訂單時間：</p>\n" +
                "                <div class=\"time\">" + QDateUtil.now(true) + "</div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>";
        sb.append(MailConstants.PREFIX)
            .append(s)
                .append(MailStyleConstants.SUCCESS)
            .append(MailConstants.SUFFIX);
        mailTool.sendHtml(property.getRecipient(), MailSubjectConstants.CHECKOUT_SUCCESS, sb.toString());
    }
    /**
     * 告诉客户，下单失败
     */
    public void notifyCheckoutError(String errorMsg) {
        StringBuilder sb = new StringBuilder();
        String s = "<div class=\"page\">\n" +
                "        <div class=\"banner\">\n" +
                "            <div class=\"fx-l\">\n" +
                "                <img class=\"img\" src=\"https://img1.baidu.com/it/u=3999660839,2221661149&fm=253&fmt=auto&app=138&f=JPEG?w=300&h=300\"/>\n" +
                "                <h2 class=\"success pl\">下單失敗</h2>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <div class=\"item\">\n" +
                "                <p>狀態：</p>\n" +
                "                <div class=\"status success\">失敗</div>\n" +
                "            </div>\n" +
                "            <div class=\"item\">\n" +
                "                <p>訂單時間：</p>\n" +
                "                <div class=\"time\">" + QDateUtil.now(true) + "</div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>";
        sb.append(MailConstants.PREFIX)
                .append(s)
                .append(MailStyleConstants.SUCCESS)
                .append(MailConstants.SUFFIX);
        mailTool.sendHtml(property.getRecipient(), MailSubjectConstants.CHECKOUT_ERROR, sb.toString());
    }

    /**
    * 告诉客户，订单退款成功
    */
    public void notifyRefundSuccess(Order order, RefundedRecord record) {
        StringBuilder sb = new StringBuilder();
        String s = "<div class=\"page\">\n" +
                "        <div class=\"banner\">\n" +
                "            <div class=\"fx-l\">\n" +
                "                <img class=\"img\" src=\"https://img2.baidu.com/it/u=516166943,3974957162&fm=253&fmt=auto&app=138&f=JPEG?w=324&h=324\"/>\n" +
                "                <h2 class=\"success pl\">退單成功</h2>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <div class=\"item\">\n" +
                "                <p>狀態：</p>\n" +
                "                <div class=\"status success\">成功</div>\n" +
                "            </div>\n" +
                "            <div class=\"item\">\n" +
                "                <p>訂單編號：</p>\n" +
                "                <div class=\"status success\">" + order.getOrder_id() + "</div>\n" +
                "            </div>\n" +
                "            <div class=\"item\">\n" +
                "                <p>退款金額：</p>\n" +
                "                <div class=\"price\">" + record.getRefunded_price() + "</div>\n" +
                "            </div>\n" +
                "            <div class=\"item\">\n" +
                "                <p>退款時間：</p>\n" +
                "                <div class=\"time\">" + QDateUtil.now(true) + "</div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>";
        sb.append(MailConstants.PREFIX)
                .append(s)
                .append(MailStyleConstants.SUCCESS)
                .append(MailConstants.SUFFIX);
        mailTool.sendHtml(property.getRecipient(), MailSubjectConstants.CHECKOUT_SUCCESS, sb.toString());
    }
}
