package com.jaque.testUtils;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

import javax.imageio.ImageIO;

public class TestUtils {
	
	
	/**
	 * 获取固定长度的随机字符串
	 * @param length 长度
	 * @return 但会字符串
	 */
	public static String getRandomString(int length){
		//定义一个字符串（A-Z，a-z，0-9）即62位；
	    //String str="一丁七万丈三三上下不与丐丑专且世丘丙业丛东丝丢两严丧个中丰串临丸丹为主丽举乃久么义之乌乍乎乏乐乒乓乔乖乘乙九乞也习乡书买乱乳了予争事二于亏云互五井亚些亡交亥亦产亩享京亭亮亲人亿什仁仅仆仇今介仍从仑仓仔他仗付仙代令以仪们仰仲件价任份仿企伊伍伏伐休众优伙会伞伟传伤伦伪伯估伴伶伸伺似佃但位低住佑体何余佛作你佣佩佳使侄侈例侍供依侠侣侥侦侧侨侮侯侵便促俄俊俏俐俗俘保信俩俭修俯俱俺倍倒倔倘候倚借倡倦债值倾假偎偏做停健偶偷偿傀傅傍储催傲傻像僚僧僵僻儒儡儿允元兄充兆先光克免兑兔党兜兢入全八公六兰共关兴兵其具典养兼兽冀内冈册再冒冕冗写军农冠冤冬冯冰冲决况冶冷冻净凄准凉凌减凑凛凝几凡凤凫凭凯凰凳凶凸凹出击函凿刀刁刃分切刊刑划列刘则刚创初删判刨利别刮到制刷券刹刺刻刽剂剃削前剑剔剖剥剧剩剪副割剿劈力劝办功加务劣动助努劫励劲劳势勃勇勉勋勒勘募勤勺勾勿匀包匆匈匕化北匙匠匣匪匹区医匾匿十千升午半华协卑卒卓单卖南博卜占卡卢卤卦卧卫印危即却卵卷卸卿厂厅历厉压厌厕厘厚原厢厦厨去县参又叉及友双反发叔取受变叙叛叠口古句另叨只叫召叭叮可台史右叶号司叹叼叽吁吃各吆合吉吊同名后吏吐向吓吕吗君吝吞吟吠否吧吨吩含听吭吮启吱吴吵吸吹吻吼呀呆呈告呐呕员呛呜呢周味呵呻呼命咆和咏咐咒咕咖咙咧咨咪咬咱咳咸咽哀品哄哆哈响哎哑哗哟哥哨哩哪哭哮哲哺哼唁唆唇唉唐唠唤唧唬售唯唱唾啃啄商啊啡啤啥啦啰啸啼喂善喇喉喊喘喜喝喧喳喷喻嗅嗓嗜嗡嗤嗦嗽嘀嘁嘉嘱嘲嘴嘶嘹嘿器噩噪嚎嚣嚷嚼囊囚四回因团囤园困囱围固国图圃圆圈土圣在地场圾址均坊坎坏坐坑块坚坛坝坞坟坠坡坤坦坪坯坷垂垃垄型垒垛垢垦垫垮埂埃埋城域埠培基堂堆堕堡堤堪堰堵塌塑塔塘塞填境墅墓墙增墨墩壁壕壤士壮声壳壶壹处备复夏夕外多夜够大天太夫夭央夯失头夷夸夹夺奄奇奈奉奋奏契奔奕奖套奠奢奥女奴奶奸她好如妄妆妇妈妒妓妖妙妥妨妹妻姆姊始姐姑姓委姚姜姥姨姻姿威娃娄娇娘娜娩娱娶婆婉婚婴婶婿媒媚媳嫁嫂嫉嫌嫡嫩嬉子孔孕字存孙孝孟季孤学孩孵孽宁它宅宇守安宋完宏宗官宙定宛宜宝实宠审客宣室宦宪宫宰害宴宵家容宽宾宿寂寄密寇富寒寓寝寞察寡寥寨寸对寺寻导寿封射将尉尊小少尔尖尘尚尝尤就尸尺尼尽尾尿局屁层居屈屉届屋屎屏屑展属屠屡履屯山屹屿岁岂岔岖岗岛岩岭岳岸峡峦峭峰峻崇崎崔崖崩崭嵌巍川州巡巢工左巧巨巩巫差己已巴巷巾币市布帅帆师希帐帕帖帘帚帜帝带席帮常帽幅幌幔幕幢干干平年并幸幻幼幽广庄庆庇床序庐库应底店庙府庞废度座庭庵庶康庸廉廊廓延廷建开异弃弄弊式弓引弛弟张弥弦弧弯弱弹强归当录形彤彩彪彬彭彰影役彻彼往征径待很徊律徐徒徒得徘御循微德徽心必忆忌忍志忘忙忠忧快忱念忽忿怀态怎怒怔怕怖怜思怠急性怨怪怯总恃恋恍恐恒恕恢恤恨恩恬恭息恰恳恶恼悄悉悍悔悟悠患悦您悬悯悲悴悼情惊惋惑惕惜惠惦惧惨惩惫惭惯惰想惶惹愁愈愉意愕愚感愤愧愿慈慌慎慕慢慧慨慰慷憋憎憔憨憾懂懈懊懒懦戈戏成我戒或战戚截戳戴户房所扁扇手才扎扑扒打扔托扛扣执扩扫扬扭扮扯扰扳扶批扼找承技抄把抑抒抓投抖抗折抚抛抠抡抢护报披抬抱抵抹押抽拂拄担拆拇拉拌拍拐拒拓拔拖拗拘拙招拜拟拢拣拥拦拧拨择括拭拯拱拳拴拷拼拾拿持挂指按挎挑挖挚挟挠挡挣挤挥挨挪挫振挺挽捂捅捆捉捌捍捎捏捐捕捞损捡换捣捧据捶捷捺捻掀掂授掉掌掏掐排掖掘掠探接控推掩措掰掷掸掺揉揍描提插揖握揣揩揪揭援揽搀搁搂搅搏搓搔搜搞搪搬搭携摄摆摇摊摔摘摧摩摸摹撇撑撒撕撞撤撩撬播撮撰撵撼擂擅操擎擒擦攀攒攘支收改攻放政故效敌敏救教敛敞敢散敦敬数敲整敷文斋斑斗料斜斟斤斥斧斩断斯新方施旁旅旋族旗无既日旦旧旨早旬旭旱时旷旺昂昆昌明昏易昔昙星映春昧昨昭是昵昼显晃晋晌晒晓晕晚晤晦晨普景晰晴晶智晾暂暇暑暖暗暮暴曙曲更曹曼曾替最月有朋服朗望朝期朦木未末本术朱朴朵机朽杀杂权杆杈杉李杏材村杖杜束杠条来杨杭杯杰松板极构枉析枕林枚果枝枢枣枪枫枯架枷柄柏某柑柒染柔柜柠查柬柱柳柴柿栅标栈栋栏树栓栖栗校株样核根格栽桂桃桅框案桌桐桑档桥桦桨桩桶梁梅梆梗梢梦梧梨梭梯械梳检棉棋棍棒棕棘棚棠森棱棵棺椅植椎椒椭椰椿楔楚楞楣楷楼概榄榆榔榕榛榜榨榴槐槽樊樟模横樱橄橘橙橡橱檀檐檩檬欠次欢欣欧欲欺款歇歉歌止正此步武歧歪歹死歼殃殉殊残殖殴段殷殿毁毅母每毒比毕毙毛毡毫毯氏民氓气氛氢氧氨氮氯水永汁求汇汉汗汛汞江池污汤汪汰汹汽沃沈沉沐沙沛沟没沥沦沧沪沫沮河沸油治沼沽沾沿泄泉泊泌法泛泞泡波泣泥注泪泰泳泵泻泼泽洁洋洒洗洛洞津洪洲活洼洽派流浅浆浇浊测济浑浓浙浦浩浪浮浴海浸涂消涉涌涎涕涛涝涡涣涤润涧涨涩涮涯液涵淀淆淋淌淑淘淡淤淫淮深淳混淹添清渊渐渔渗渠渡渣渤温港渴游渺湃湖湘湾湿溃溅溉源溜溢溪溯溶溺滋滑滓滔滚滞满滤滥滨滩滴漂漆漏漓演漠漩漫漱漾潘潜潦潭潮澄澈澎澜澡澳激濒瀑灌火灭灯灰灵灶灸灼灾灿炉炊炎炒炕炫炬炭炮炸点炼烁烂烈烘烙烛烟烤烦烧烫热烹焊焕焙焚焦焰然煌煎煞煤照煮熄熊熏熔熙熟熬燃燎燕燥爆爪爬爱爵父爷爸爹爽片版牌牍牙牛牡牢牧物牲牵特牺犀犁犬犯状犹狂狈狐狗狞狠狡独狭狮狰狱狸狼猎猖猛猜猩猪猫猬献猴猾猿玄率玉王玖玛玩玫环现玲玷玻珊珍珠班球琅理琉琐琢琳琴琼瑞瑟瑰璃璧瓜瓢瓣瓤瓦瓮瓶瓷甘甚甜生甥用甩甫田由甲申电男甸画畅界畏畔留畜略畦番畴畸疆疏疑疗疙疚疟疤疫疮疯疲疹疼疾病症痊痒痕痘痛痢痪痰痴痹瘟瘤瘦瘩瘪瘫瘸瘾癌癞癣登白百皂的皆皇皮皱皿盅盆盈益盏盐监盒盔盖盗盘盛盟目盯盲直相盹盼盾省眉看真眠眨眯眶眷眼着睁睛睡督睦睬睹瞄瞎瞒瞧瞪瞬瞭瞳瞻矗矛矢知矩矫短矮石矾矿码砂砌砍研砖砚砰破砸砾础硅硕硝硫硬确硼碉碌碍碎碑碗碘碟碧碰碱碳碴碾磁磅磕磨磷磺礁示礼社祈祖祝神祟祠祥票祭祷祸禀禁福离禽禾秀私秃秆秉秋种科秒秕秘租秤秦秧秩秫积称秸移秽稀程稍税稚稠稳稻稼稽稿穆穗穴究穷空穿突窃窄窍窑窒窖窗窘窜窝窟窥窿立竖站竞竟章竣童竭端竹竿笆笋笑笔笙笛笤符笨第笼等筋筏筐筑筒答策筛筝筷筹签简箍箕算管箩箫箭箱篇篓篙篡篮篱篷簇簸簿籍米类籽粉粒粗粘粟粤粥粪粮粱粹精糊糕糖糙糜糟糠糯系紊素索紧紫累絮繁纠红纤约级纪纫纬纯纱纲纳纵纷纸纹纺纽线练组绅细织终绊绍绎经绑绒结绕绘给络绝绞统绢绣继绩绪续绰绳维绵绷绸综绽绿缀缅缆缎缓缔缕编缘缚缝缠缤缨缩缭缰缴缸缺罐网罕罗罚罢罩罪置署羊美羔羞羡群羹羽翁翅翎翔翘翠翩翰翻翼耀老考者而耍耐耕耕耗耙耳耸耻耽耿聂聊聋职联聘聚聪肃肄肆肉肋肌肖肘肚肛肝肠股肢肤肥肩肪肮肯育肴肺肾肿胀胁胃胆背胎胖胚胜胞胡胧胯胰胳胶胸能脂脆脉脊脏脐脑脓脖脚脯脱脸脾腊腋腌腐腔腕腥腮腰腹腺腻腾腿膀膊膏膘膛膜膝膨膳臀臂臊臣自臭至致臼舀舅舆舌舍舒舔舞舟航般舰舱舵舶舷船艇艘良艰色艳艺艾节芋芍芒芙芜芝芥芦芬芭芯花芳芹芽苇苍苏苔苗苛苞苟若苦苫英苹茁茂范茄茅茉茎茧茫茬茴茵茶茸荆草荐荒荔荚荞荠荡荣荤荧药荷荸莉莫莱莲获莹莺莽菇菊菌菜菠菩菱菲萄萌萍萎萝萤营萧萨落著葛葡董葫葬葱葵蒂蒋蒙蒜蒲蒸蒿蓄蓉蓖蓝蓬蔑蔓蔗蔚蔫蔬蔼蔽蕉蕊蕴蕾薄薇薛薪薯藏藐藕藤藻蘑蘸虎虏虐虑虚虫虱虹虽虾蚀蚁蚂蚊蚌蚓蚕蚜蚣蚤蚪蚯蛀蛆蛇蛉蛋蛔蛙蛛蛤蛮蛹蛾蜀蜂蜈蜒蜓蜕蜗蜘蜜蜡蜻蝇蝉蝌蝎蝗蝙蝠蝴蝶螃融螟螺蟀蟆蟋蟹蠕蠢血衅行衍衔街衙衡衣补表衩衫衬衰衷袁袄袋袍袒袖袜被袭袱裁裂装裆裉裕裙裤裳裸裹褂褐褒褥襟西要覆见观规觅视览觉角解触言誉誊誓警譬计订认讥讨让训议讯记讲讳讶许讹论讼讽设访诀证评诅识诈诉诊词译试诗诚话诞诡询该详诫诬语误诱诲说诵请诸诺读诽课谁调谅谆谈谊谋谍谎谐谒谓谚谜谢谣谤谦谨谬谭谱谴谷豁豆豌象豪豫豹豺貌贝贞负贡财责贤败账货货质贩贪贫贬购贮贯贰贱贴贵贸费贺贼贾贿赁赂赃资赊赋赌赎赏赐赔赖赘赚赛赞赠赡赢赤赦赫走赴赵赶起趁超越趋趟趣足趴趾跃跋跌跑跛距跟跨跪路跳践跷跺踊踏踢踩踪踱蹂蹄蹈蹋蹦蹬蹭蹲躁躏身躬躯躲躺车轧轨轩转轮软轰轴轻载轿较辅辆辈辉辐辑输辕辖辙辛辜辞辟辣辨辩辫辰辱边辽达迁迂迄迅过迈迎运近返还这进远违连迟迫述迷迹追退送适逃逆选逊透逐递途逗通逛逝逞速造逢逮逸逻逼逾遂遇遍遏道遗遣遥遭遮遵避邀邑邓邢那邦邪邮邻郁郊郎郑部郭都鄙酌配酒酗酝酣酥酪酬酱酵酷酸酿醇醉醋醒采释里重野量金鉴针钉钓钙钝钞钟钠钢钥钦钧钩钮钱钳钻钾铁铃铅铆铐铛铜铝铡铣铭铲银铸铺链销锁锄锅锈锉锋锌锐错锚锡锣锤锥锦锨锭键锯锰锹锻镀镇镊镐镜镣镰镶长门闪闭问闯闰闲间闷闸闹闺闻闽阀阁阅阎阐阔队阱防阳阴阵阶阻阿附际陆陈陋陌降限陕陡院除陨险陪陵陶陷隅隆随隐隔隘隙障隧隶难雀雁雄雅集雇雌雏雕雨雪雳零雷雹雾需震霉霍霎霜霞露霸霹青靖静非靠靡面革靴靶鞋鞍鞠鞭韧韩韭音韵页顶顷项顺须顽顾顿颁颂预颅领颇颈颊频颓颖颗题颜额颠颤风飒飘飞食餐饥饭饮饰饱饲饵饶饺饼饿馁馅馆馋馍馏馒首香马驮驯驰驱驳驴驶驹驻驼驾骂骄骆骇验骏骑骗骚骡骤骨髓高鬓鬼魁魂魄魏魔鱼鲁鲜鲤鲫鲸鳄鳍鳖鳞鸟鸠鸡鸣鸥鸦鸭鸯鸳鸵鸽鸿鹃鹅鹉鹊鹏鹤鹦鹰鹿麦麸麻黄黍黎黑黔默鼎鼓鼠鼻齐齿龄龙龟";
	    String str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    //由Random生成随机数
	    Random random=new Random();  
	    StringBuffer sb=new StringBuffer();
	    //长度为几就循环几次
	    for(int i=0; i<length; ++i){
	    	//产生0-61的数字
	    	int number=random.nextInt(52);
	    	//将产生的数字通过length次承载到sb中
	    	sb.append(str.charAt(number));
	    }
	    //将承载的字符转换成字符串
	    return sb.toString();
	}
	/**
	 * 获取随机手机号
	 * @return	返回手机号
	 */
	public static String getRandomPhone() {

	    Random random=new Random();  
	    StringBuffer sb=new StringBuffer();
	    //长度为几就循环几次
	    sb.append(1);
	    for(int i=1; i<11; ++i){
	    	//产生0-61的数字
	    	int number=random.nextInt(10);
	    	//将产生的数字通过length次承载到sb中
	    	sb.append(number);
	    }
	    //将承载的字符转换成字符串
	    return sb.toString();
	}
    /**
     * 判断正在执行用例的服务器是否是jenkin部署的服务器
     * @return 返回是否是jenkins服务器
     * @throws SocketException 网络信息获取异常
     */
    public static boolean isTestServer() throws SocketException {
    	
		String JenkinHome = System.getenv("CATALINA_HOME");
		if(null == JenkinHome || "".equals(JenkinHome)) {
			return false;
		}else {
			return true;
		}
		
    	/*String serverIp = PropertyPraser.getProperty("jenkinsServerIp");
    	Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		InetAddress ip = null;
		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
			//System.out.println(netInterface.getName());
			Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = (InetAddress) addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address && ip.getHostAddress().equals(serverIp)) {
					//System.out.println("本机的IP = " + ip.getHostAddress());
					return true;
				}
			}
		}
		return false;*/
    }

	/**
	 * 以年月日时分秒毫秒的格式获取当前时间的字符串
	 * @return 返回时间字符串
	 */
	public static String getTimeStamp() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	/**
	 * 将整个屏幕截图保存为指定文件
	 * @param filePath 截图后的保存路径
	 */
	public static void takeScreenSnap(String filePath) {

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		try {
			Robot robot = new Robot();
			BufferedImage bufferedImage = robot.createScreenCapture(new Rectangle(d.width, d.height));
			ImageIO.write(bufferedImage, filePath.substring(filePath.length()-3), new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取工作目录
	 * @return 返回工作目录
	 * @throws URISyntaxException 异常
	 */
	public static String getWorkPath() throws URISyntaxException {
		String workPath = TestUtils.class.getClassLoader().getResource("").toURI().getPath();
		return workPath;
	}
}
