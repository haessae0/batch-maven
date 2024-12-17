package com.haessae0.batch.processor;

import com.haessae0.batch.Util.DateUtil;
import com.haessae0.batch.dto.*;
import com.haessae0.batch.mapper.MailRtlWeeklyMapper;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

public class MrctItemProcessor implements ItemProcessor<String, MailCompositeItem> {

    private final MailRtlWeeklyMapper mailRtlWeeklyMapper;
    private final String curDate;


    public MrctItemProcessor(MailRtlWeeklyMapper mailRtlWeeklyMapper) {
        this.mailRtlWeeklyMapper = mailRtlWeeklyMapper;
        this.curDate = DateUtil.getCurDate("yyyyMMdd");
    }

    @Override
    public MailCompositeItem process(String mrctId) throws Exception {
        MailCompositeItem compositeItem = new MailCompositeItem();

        List<KeyManDto> selectKeyMan = mailRtlWeeklyMapper.selectKeyMan(mrctId);

        for (KeyManDto keyMan : selectKeyMan) {
            String email = keyMan.getEmail();
            List<RtlDto> selectRtlContents = mailRtlWeeklyMapper.selectRtlList(curDate, mrctId);

            StringBuilder rtl = new StringBuilder();
            rtl.append("<html><body>");
            rtl.append("<div style='Font-Family:Malgun Gothic; Font-Size:13px; height:180px;'> 안녕하세요<br><br>");
            rtl.append("KG모빌리언스 입니다.<br>");
            rtl.append("정산내역 공유 드립니다.<br><br>");
            rtl.append("정산금액 불일치 시 아래 주소로 회신 부탁 드립니다. <br><br>");
            rtl.append("- 회신 주소 : ").append("test@naver.com").append("<br><br>");
            rtl.append("감사합니다. <br>");
            rtl.append("</div><br>");
            rtl.append("<div><br>");
            rtl.append("<table border='1' style='Text-Align:Center;Font-Family:Malgun Gothic;Font-Size:12px;border-Collapse:Collapse;border-Spacing:0;'>");
            rtl.append("<tr style='Background-Color:#add8e6;Font-Weight:Bold;'>");
            rtl.append("<th width=190 height=40 style='Padding:4 15 4 15px;'>거래년월일</th>");
            rtl.append("<th width=190 style='Padding:4 15 4 15px;'>가맹점명</th>");
            rtl.append("<th width=170 style='Padding:4 15 4 15px;'>계약상품</th>");
            rtl.append("<th width=90 style='Padding:4 15 4 15px;'>거래건수</th>");
            rtl.append("<th width=150 style='Padding:4 15 4 15px;'>거래금액</th>");
            rtl.append("<th width=150 style='Padding:4 15 4 15px;'>수수료율</th>");
            rtl.append("<th width=150 style='Padding:4 15 4 15px;'>공급가</th>");
            rtl.append("<th width=150 style='Padding:4 15 4 15px;'>부가세</th>");
            rtl.append("<th width=150 style='Padding:4 15 4 15px;'>수수료합</th>");
            rtl.append("<th width=150 style='Padding:4 15 4 15px;'>정산예정금액</th>");
            rtl.append("</tr>");


            String MRCTNM = "";
            String CNTR_PRDT_NM = "";

            if (selectRtlContents != null && !selectRtlContents.isEmpty()) {
                for (RtlDto rtlDto : selectRtlContents) {
                    MRCTNM = rtlDto.getMrctNm();
                    CNTR_PRDT_NM = rtlDto.getCntrPrdtNm();

                    String BILL_CNT = rtlDto.getBillCnt();
                    String BILL_AMT = rtlDto.getBillAmt();
                    String FEE_RT = rtlDto.getFeeRt();
                    String SPL_AMT = rtlDto.getSplAmt();
                    String VAT = rtlDto.getVat();
                    String FEE = rtlDto.getFee();
                    String RCPT_SCH_AMT = rtlDto.getRcptSchAmt();

                    rtl.append("<tr>");
                    rtl.append("<td height=40 style='Padding:4 15 4 15px;text-align:center'>").append(rtlDto.getBillDt()).append("</td>");
                    rtl.append("<td style='Padding:4 15 4 15px;text-align:left'>").append(MRCTNM).append("</td>");
                    rtl.append("<td style='Padding:4 15 4 15px;Background-Color:#ffd732;text-align:left'>").append(CNTR_PRDT_NM).append("</td>");
                    rtl.append("<td style='Padding:4 15 4 15px;text-align:right;'>").append(BILL_CNT).append("</td>");
                    rtl.append("<td style='Padding:4 15 4 15px;color:red;text-align:right;'>").append(BILL_AMT).append("</td>");
                    rtl.append("<td style='Padding:4 15 4 15px;text-align:right;'>").append(FEE_RT).append("%</td>");
                    rtl.append("<td style='Padding:4 15 4 15px;text-align:right;'>").append(SPL_AMT).append("</td>");
                    rtl.append("<td style='Padding:4 15 4 15px;text-align:right;'>").append(VAT).append("</td>");
                    rtl.append("<td style='Padding:4 15 4 15px;text-align:right;'>").append(FEE).append("</td>");
                    rtl.append("<td style='Padding:4 15 4 15px;text-align:right;Background-Color:#dcdcdc;'>").append(RCPT_SCH_AMT).append("</td>");
                    rtl.append("</tr>");
                }
            }
            rtl.append("</table>");
            rtl.append("</div><br>");
            rtl.append("</body></html>");

            MailIdDto mailIdDto = mailRtlWeeklyMapper.getMailId(curDate, mrctId);
            String mailId = mailIdDto.getMailId();

            MailItem mailItem = new MailItem();
            mailItem.setMailAddr(email);
            mailItem.setMailTitle("[KG모빌리언스] [" + (MRCTNM == null ? "" : MRCTNM) + " ] " + curDate + " " + (CNTR_PRDT_NM == null ? "" : CNTR_PRDT_NM) + " 정산의 건");
            mailItem.setHtmlContent(rtl.toString());
            mailItem.setMailId(mailId);

            compositeItem.getMails().add(mailItem);
        }

        return compositeItem;
    }
}
