package com.mrgao.demo.utils;

/**
 * @author Mr.Gao
 * @date 2024/3/12 13:54
 * @apiNote:
 */
public class RemoveString {
    public static void main(String[] args) {
        //String rs = "一般项目:软件开发;网络与信息安全软件开发:技术服务、技术开发、技术咨询、技术交流、技术转让、技术推广;信息技术咨询服务;智能控制系统集成;人工智能基础软件开发;人工智能基础资源与技术平台:人工智能通用应用系统;数据处理和存储支持服务:计算机软硬件及辅助设备批发;计算机软硬件及辅助设备零售:互联网安全服务;互联网数据服务:数字文化创意内容应用服务;会议及展览服务:信息咨询服务(不含许可类信息咨询服务);企业管理;互联网销售(除销售需要许可的商品):广告设计、代理(除依法须经批准的项目外,凭营业执照依法自主开展经营活动)";
        String rs = "一般项目文具用品零售办公用品销售互联网销售除销售需要许可的商品纸制品销售教学用模型及教具销售办公设备耗材销售办公设备销售日用百货销售计算机软硬件及辅助设备零售成人情趣用品销售不含药品医疗器械食品互联网销售仅销售预包装食品保健食品预包装销售食品销售仅销售预包装食品。除依法须经批准的项目外凭营业执照依法自主开展经营活动";
        // ：，、（）。
        // :
        rs = rs.replace(":", "");
        rs = rs.replace(";", "");
        rs = rs.replace(",", "");
        rs = rs.replace("、", "");
        rs = rs.replace("(", "");
        rs = rs.replace(")", "");
        rs = rs.replace("\n", "");
        rs = rs.replace("。", "");
        System.out.println(rs);

        String linkUrl = "https://mchntapi.fuiou.com/wxMchntMng.fuiou?action=elecContract&sign=FA0B4FDB34954D8D557D165801D4CDF7";
        System.out.println(linkUrl.substring(linkUrl.lastIndexOf("wxMchntMng")));
        System.out.println(linkUrl.lastIndexOf("wxMchntMng"));
        String suffixUrl = linkUrl.substring(linkUrl.lastIndexOf("wxMchntMng"));
        linkUrl = "http://www-1.fuiou.com:28090/wmp" + "/" + suffixUrl;
        //linkUrl = linkUrl.replace("https://mchntapi.fuioupay.com", "http://www-1.fuiou.com:28090/wmp");
        System.out.println(linkUrl);
    }


}
