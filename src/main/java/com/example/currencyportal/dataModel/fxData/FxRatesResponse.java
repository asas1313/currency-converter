package com.example.currencyportal.dataModel.fxData;

import com.sun.xml.txw2.annotation.XmlElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "FxRates")
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings("unused")
public class FxRatesResponse {

        private List<CurrencyItem> item = new ArrayList<>();

        @XmlElement("FxRate")
        public List<CurrencyItem> getItem() {
                return item;
        }

        public void setItem(List<CurrencyItem> item) {
                this.item = item;
        }

        public FxRatesResponse() {
        }

        public FxRatesResponse(List<CurrencyItem> item) {
                this.item = item;
        }

    public void buildFromXmlDocument(Document document) {
            Element root = document.getDocumentElement();
            NodeList nList = document.getElementsByTagName("FxRate");

            for (int i = 0; i < nList.getLength(); i++) {
                    Node node = nList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;
                            String tp = element.getElementsByTagName("Tp").item(0).getTextContent();
                            String dt = element.getElementsByTagName("Dt").item(0).getTextContent();

                            List<CurrencyAmount> currencyAmountList = new ArrayList<>();
                            NodeList ccyAmt1NodeList = element.getElementsByTagName("CcyAmt");
                            for (int j = 0; j < ccyAmt1NodeList.getLength(); j++) {
                                    Node ccyAmtNode = ccyAmt1NodeList.item(j);
                                    if (ccyAmtNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element ccyAmt = (Element) ccyAmtNode;
                                            String ccy = ccyAmt.getElementsByTagName("Ccy").item(0).getTextContent();
                                            String amt = ccyAmt.getElementsByTagName("Amt").item(0).getTextContent();
                                            currencyAmountList.add(new CurrencyAmount(ccy, amt));
                                    }
                            }

                            item.add(new CurrencyItem(tp, dt, currencyAmountList));
                    }
            }
    }
}
