package it.dy.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import it.dy.util.JSONUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author w5489
 * @Description
 * @date 2022/11/15 21:40
 */
public class RegxUrl {

    static List<String> URLS=new ArrayList<>();

    static {
        String urls="[\n" +
                "    \"/oes-csas-manage/detail/coverImgLinks\",\n" +
                "    \"/oes-csas-manage/content/videos/search/detail\",\n" +
                "    \"/oes-csas-words/word/save\",\n" +
                "    \"/oes-csas-manage/announcement/refresh\",\n" +
                "    \"/oes-csas-manage/ip/list\",\n" +
                "    \"/oes-csas-manage/statistics/getAuditWeekMediumData\",\n" +
                "    \"/oes-csas-manage/content/vod-program-shells/search/detail\",\n" +
                "    \"/oes-csas-manage/group/detail\",\n" +
                "    \"/oes-csas-manage/aia-record/retry\",\n" +
                "    \"/oes-csas-manage/detail/loadAsset\",\n" +
                "    \"/oes-csas-manage/aia-task/backCheckDetail\",\n" +
                "    \"/oes-csas-manage/aisleAi/list\",\n" +
                "    \"/oes-csas-manage/audit-task/taskCalendars\",\n" +
                "    \"/oes-csas-manage/detail/videoLinks\",\n" +
                "    \"/oes-csas-manage/assetRiskInfo/detail\",\n" +
                "    \"/oes-csas-words/word/learn\",\n" +
                "    \"/oes-csas-manage/aisle/allOfAisle\",\n" +
                "    \"/oes-csas-words/wordGroup/save\",\n" +
                "    \"/oes-csas-manage/aisleUser/remove\",\n" +
                "    \"/oes-csas/menu/safe-risk-label\",\n" +
                "    \"/oes-csas-manage/check/page\",\n" +
                "    \"/oes-csas-manage/log/outer-url/refreshAllData\",\n" +
                "    \"/oes-csas-manage/audit-task/taskList\",\n" +
                "    \"/oes-csas-manage/checkSub/auditStatus\",\n" +
                "    \"/oes-csas-manage/statistics/auditDailyStatistics\",\n" +
                "    \"/oes-csas-words/wordSource/listName\",\n" +
                "    \"/oes-csas-manage/aisleKeyword/template\",\n" +
                "    \"/oes-csas-manage/userRisk/user/delUserRisk\",\n" +
                "    \"/oes-csas-manage/label/save\",\n" +
                "    \"/oes-csas-manage/offLineTask/detail-export\",\n" +
                "    \"/oes-csas-manage/statistics/aiInteractiveStatisticalPage\",\n" +
                "    \"/oes-csas-manage/group/updateAuditGroup\",\n" +
                "    \"/oes-csas-manage/content/getQueryStatus\",\n" +
                "    \"/oes-csas-manage/audit/findBigAuditLogByPage\",\n" +
                "    \"/oes-csas-manage/content/submit\",\n" +
                "    \"/oes-csas-manage/remarkPhrase/get\",\n" +
                "    \"/oes-csas-manage/remarkPhrase/update\",\n" +
                "    \"/oes-csas-manage/aisleKeyword/download\",\n" +
                "    \"/oes-csas-manage/statisticsExcel/auditDailyStatisticsExcel\",\n" +
                "    \"/oes-csas-manage/check-field-conf/delete\",\n" +
                "    \"/oes-csas-manage/audit/fetch\",\n" +
                "    \"/oes-csas/menu/aia-intelligent/auditLogQuery\",\n" +
                "    \"/oes-csas-manage/announcement/update\",\n" +
                "    \"/oes-csas-manage/audit-type-map/sync\",\n" +
                "    \"/oes-csas-manage/ip/upload\",\n" +
                "    \"/oes-csas-manage/riskLabel/download\",\n" +
                "    \"/oes-csas/menu/sys/dict\",\n" +
                "    \"/oes-csas-words/word/remove\",\n" +
                "    \"/oes-csas-manage/uploadUser/remove\",\n" +
                "    \"/oes-csas/menu/words/words-learn\",\n" +
                "    \"/oes-csas-manage/content/export\",\n" +
                "    \"/oes-csas-manage/configuration/updateElement\",\n" +
                "    \"/oes-csas-manage/detail/recommendAdaptor\",\n" +
                "    \"/oes-csas-manage/aisle/getAllRoles\",\n" +
                "    \"/oes-csas/menu/aisle/aisle-manage\",\n" +
                "    \"/oes-csas-manage/reviewLibrary/update\",\n" +
                "    \"/oes-csas-manage/user/risk/grade/saveOrUpdate\",\n" +
                "    \"/oes-csas-manage/dis/saveDisAisle\",\n" +
                "    \"/oes-csas-manage/aisle/get-list\",\n" +
                "    \"/oes-csas/menu/safe-risk-label/accountRiskTag\",\n" +
                "    \"/oes-csas-manage/statisticsExcel/AuditStatisticsExcel\",\n" +
                "    \"/oes-csas-manage/aia-task/backCheckBatchDetailExport\",\n" +
                "    \"/oes-csas-manage/sysDictData/getAll\",\n" +
                "    \"/oes-csas-manage/assetRiskInfo/sync\",\n" +
                "    \"/oes-csas-manage/offLineTask/template\",\n" +
                "    \"/oes-csas-manage/configuration/addElement\",\n" +
                "    \"/oes-csas-manage/audit/checkSecondSubmitAsset\",\n" +
                "    \"/oes-csas-words/wordSource/save\",\n" +
                "    \"/oes-csas-words/word/refresh\",\n" +
                "    \"/oes-csas/menu/aisle/aisle-user\",\n" +
                "    \"/oes-csas-manage/dis/updateDisAisle\",\n" +
                "    \"/oes-csas-words/wordGroup/remove\",\n" +
                "    \"/oes-csas-manage/detail/outer-url/getSubsetInformation\",\n" +
                "    \"/oes-csas-manage/detail/pidOpt\",\n" +
                "    \"/oes-csas/menu/aisle-audit\",\n" +
                "    \"/oes-csas-manage/configuration/delete\",\n" +
                "    \"/oes-csas-manage/log/retry\",\n" +
                "    \"/oes-csas-manage/group/account/list\",\n" +
                "    \"/oes-csas/menu/statistics/audit-todo\",\n" +
                "    \"/oes-csas-manage/assetHighRisk/list\",\n" +
                "    \"/oes-csas-words/wordGroup/list\",\n" +
                "    \"/oes-csas-words/sensitive/matchText\",\n" +
                "    \"/oes-csas-manage/riskLabel/getRiskLabelEnum\",\n" +
                "    \"/oes-csas-manage/detail/outer-url/getAssetContentInfo\",\n" +
                "    \"/oes-csas/menu/aia-intelligent\",\n" +
                "    \"/oes-csas-words/wordGroup/update\",\n" +
                "    \"/oes-csas-manage/detail/auditRemarkList\",\n" +
                "    \"/oes-csas-manage/uploadUser/download\",\n" +
                "    \"/oes-csas/menu/aisle/field-cfg\",\n" +
                "    \"/oes-csas/menu/aisle/audit-ai\",\n" +
                "    \"/oes-csas-manage/audit-type-map/pageList\",\n" +
                "    \"/oes-csas-manage/audit/tick\",\n" +
                "    \"/oes-csas-manage/reviewLibrary/refresh\",\n" +
                "    \"/oes-csas-manage/statistics/findTime\",\n" +
                "    \"/oes-csas-manage/aisle/aisleInfoByRuleName\",\n" +
                "    \"/oes-csas-manage/aisleUser/template\",\n" +
                "    \"/oes-csas-words/word/list\",\n" +
                "    \"/oes-csas-manage/sysDictType/list\",\n" +
                "    \"/oes-csas-words/sensitive/sensitiveList\",\n" +
                "    \"/oes-csas-manage/userRisk/user/saveOrUpdateUserRisk\",\n" +
                "    \"/oes-csas-manage/reviewLibrary/get\",\n" +
                "    \"/oes-csas-manage/userRisk/saveOrUpdateUserRisk\",\n" +
                "    \"/oes-csas-words/wordSource/update\",\n" +
                "    \"/oes-csas/menu/safe-risk-label/accountRiskTagSyn\",\n" +
                "    \"/oes-csas-manage/aisleUser/download\",\n" +
                "    \"/oes-csas-manage/shieldType/save\",\n" +
                "    \"/oes-csas/menu/audit-task\",\n" +
                "    \"/oes-csas/menu/riskTagPot\",\n" +
                "    \"/oes-csas-manage/group/updateAccount\",\n" +
                "    \"/oes-csas-manage/aia-record/audit/result\",\n" +
                "    \"/oes-csas/menu/aisle/audit-report\",\n" +
                "    \"/oes-csas-manage/uploadUser/save\",\n" +
                "    \"/oes-csas-manage/content/delList\",\n" +
                "    \"/oes-csas-manage/detail/outer-url/getShowInfo\",\n" +
                "    \"/oes-csas-manage/statisticsExcel/auditWeekDataStatisticsExcel\",\n" +
                "    \"/oes-csas/menu/aisle\",\n" +
                "    \"/oes-csas-manage/assetHighRisk/users\",\n" +
                "    \"/oes-csas-manage/aia-task/backCheckBatchList\",\n" +
                "    \"/oes-csas-manage/riskLabel/sync\",\n" +
                "    \"/oes-csas-manage/label/update\",\n" +
                "    \"/oes-csas-manage/audit/log\",\n" +
                "    \"/oes-csas/menu/audit-task/myAuditTask\",\n" +
                "    \"/oes-csas-manage/check/save\",\n" +
                "    \"/oes-csas-manage/audit/batch\",\n" +
                "    \"/oes-csas/menu/aisle/assetId\",\n" +
                "    \"/oes-csas-manage/statistics/searchShieldData\",\n" +
                "    \"/oes-copyright-manage/api/v1/external/bind-contents\",\n" +
                "    \"/oes-csas-manage/statistics/auditWeekDataStatistics\",\n" +
                "    \"/oes-csas-manage/riskLabelGrade/delRiskLabel\",\n" +
                "    \"/oes-csas/menu/audit-task/dispatchAuditTask\",\n" +
                "    \"/oes-csas-manage/offLineTask/list\",\n" +
                "    \"/oes-csas-manage/content/queryList\",\n" +
                "    \"/oes-csas-manage/statisticsExcel/auditAvgTimeStatisticsExcel\",\n" +
                "    \"/oes-csas-manage/aisleKeyword/list\",\n" +
                "    \"/oes-csas/menu/check-ai\",\n" +
                "    \"/oes-csas-manage/user/risk/grade/list\",\n" +
                "    \"/oes-csas-manage/userRisk/user/bachSaveUserRisk\",\n" +
                "    \"/oes-csas-manage/statistics/auditAvgTimeStatistics\",\n" +
                "    \"/oes-csas-manage/rule/ruleList\",\n" +
                "    \"/oes-csas-manage/reviewLibrary/save\",\n" +
                "    \"/oes-csas-manage/group/getRoleList\",\n" +
                "    \"/oes-csas-manage/sysDictType/update\",\n" +
                "    \"/oes-csas/menu/statistics/audit\",\n" +
                "    \"/oes-csas-manage/audit/notAuditFromRedis\",\n" +
                "    \"/oes-csas-manage/userRisk/user/batchImport\",\n" +
                "    \"/oes-csas-manage/audit-task/submit\",\n" +
                "    \"/oes-csas-manage/aisleAi/get\",\n" +
                "    \"/oes-csas-manage/announcement/findOne\",\n" +
                "    \"/oes-csas-manage/assetRiskInfo/download\",\n" +
                "    \"/oes-csas-manage/detail/historicalFieldList\",\n" +
                "    \"/oes-csas/menu/statistics/audit-link\",\n" +
                "    \"/oes-csas-manage/aisleAi/save\",\n" +
                "    \"/oes-csas-manage/sysDictType/get\",\n" +
                "    \"/oes-csas/menu/words/words-type\",\n" +
                "    \"/oes-csas-manage/userRisk/user/template\",\n" +
                "    \"/oes-csas-manage/offLineTask/createTask\",\n" +
                "    \"/oes-csas-manage/statistics/safetyRiskStatistics\",\n" +
                "    \"/oes-csas-manage/assetHighRisk/remove\",\n" +
                "    \"/oes-csas-manage/configuration/queryList\",\n" +
                "    \"/oes-csas-manage/group/list\",\n" +
                "    \"/oes-csas-manage/riskLabel/allOfRiskLabelInfo\",\n" +
                "    \"/oes-csas-words/wordSource/list\",\n" +
                "    \"/oes-csas-manage/configuration/refresh\",\n" +
                "    \"/oes-csas/menu/audit-task/disChannelManage\",\n" +
                "    \"/oes-csas-manage/audit/back\",\n" +
                "    \"/oes-csas-manage/audit-type-map/save\",\n" +
                "    \"/oes-csas-manage/riskLabel/log\",\n" +
                "    \"/oes-csas-manage/aisle/remove\",\n" +
                "    \"/oes-csas-manage/check-field-conf/save\",\n" +
                "    \"/oes-csas-manage/riskLabelGrade/list\",\n" +
                "    \"/oes-csas-manage/aisleKeyword/remove\",\n" +
                "    \"/oes-csas-manage/aisleKeyword/save\",\n" +
                "    \"/oes-csas-manage/aisle/get\",\n" +
                "    \"/oes-csas-manage/riskLabel/saveOrUpdate\",\n" +
                "    \"/oes-csas-manage/detail/getLabelInfo\",\n" +
                "    \"/oes-csas/menu/sys/review\",\n" +
                "    \"/oes-csas-manage/content/imgtexts/search/detail\",\n" +
                "    \"/oes-content-api/content/asset/getPathInfo\",\n" +
                "    \"/oes-csas-manage/riskLabelGrade/treeList\",\n" +
                "    \"/oes-csas-manage/aisle/aisleInfoAll\",\n" +
                "    \"/oes-csas/menu/aisle/uploaduser\",\n" +
                "    \"/oes-csas-words/word/template\",\n" +
                "    \"/oes-csas-manage/detail/auditBtnList\",\n" +
                "    \"/oes-csas/menu/aisle/lable\",\n" +
                "    \"/oes-csas-manage/statisticsExcel/safetyRiskStatisticsExcel\",\n" +
                "    \"/oes-csas-manage/aisleAi/delete\",\n" +
                "    \"/oes-csas/menu/aia-intelligent/batchCheck\",\n" +
                "    \"/oes-csas-words/word/download\",\n" +
                "    \"/oes-csas/menu/RiskTag\",\n" +
                "    \"/oes-csas-manage/offLineTask/detail\",\n" +
                "    \"/oes-csas-manage/check/status\",\n" +
                "    \"/oes-csas-manage/content/es/videos/search\",\n" +
                "    \"/oes-csas-manage/ip/saveOrUpdate\",\n" +
                "    \"/oes-csas-manage/statistics/accounts\",\n" +
                "    \"/oes-csas-manage/statisticsExcel/auditWeekMediumDataExcel\",\n" +
                "    \"/oes-csas-manage/check/update\",\n" +
                "    \"/oes-content-manage/program/get\",\n" +
                "    \"/oes-csas-manage/aisle/getAisleTypeInfo\",\n" +
                "    \"/oes-csas-manage/top500/list\",\n" +
                "    \"/oes-csas/menu/statistics/aiData\",\n" +
                "    \"/oes-csas/menu/safe-risk-label/programRiskTag\",\n" +
                "    \"/oes-csas/menu/words/words-manage\",\n" +
                "    \"/oes-csas/menu/sys\",\n" +
                "    \"/oes-csas-manage/aisleAi/download\",\n" +
                "    \"/oes-csas-manage/userRisk/log/list\",\n" +
                "    \"/oes-csas-ai/audit/result/text\",\n" +
                "    \"/oes-csas-manage/aisle/updateAisleRole\",\n" +
                "    \"/oes-csas-manage/aia-task/createTask\",\n" +
                "    \"/oes-csas-manage/content/chkList\",\n" +
                "    \"/oes-csas-manage/uploadUser/update\",\n" +
                "    \"/oes-csas-manage/aia-record/video/result\",\n" +
                "    \"/enum/manage/block-word\",\n" +
                "    \"/oes-csas-words/word/update\",\n" +
                "    \"/oes-csas-manage/content/es/imgtexts/search\",\n" +
                "    \"/oes-csas-manage/detail/fieldStruct\",\n" +
                "    \"/oes-csas-manage/reviewLibrary/remove\",\n" +
                "    \"/oes-csas-manage/fieldCfg/list\",\n" +
                "    \"/oes-csas-manage/userRisk/user/list\",\n" +
                "    \"/oes-csas-manage/userRisk/delUserRisk\",\n" +
                "    \"/oes-csas-manage/reviewLibrary/list\",\n" +
                "    \"/oes-csas-manage/checkSub/detail\",\n" +
                "    \"/oes-csas-manage/aisle/getSource\",\n" +
                "    \"/oes-csas-manage/top500/export\",\n" +
                "    \"/oes-csas-manage/userRisk/getUserRiskEnum\",\n" +
                "    \"/oes-csas-manage/assetHighRisk/add\",\n" +
                "    \"/oes-csas/menu/statistics/RiskLabel\",\n" +
                "    \"/oes-csas-manage/content/es/vod-program-shells/search\",\n" +
                "    \"/oes-csas/menu/check-ai/task\",\n" +
                "    \"/oes-csas-manage/aia-task/template\",\n" +
                "    \"/oes-csas-manage/reviewLibrary/upload\",\n" +
                "    \"/oes-csas-manage/audit/findBigAssetAuditLogPage\",\n" +
                "    \"/oes-csas-words/shieldType/load\",\n" +
                "    \"/enum/manage/sensitive-word\",\n" +
                "    \"/oes-csas-manage/aisleAi/template\",\n" +
                "    \"/oes-csas-manage/aisle/saveOrUpdate\",\n" +
                "    \"/oes-csas/menu/statistics\",\n" +
                "    \"/oes-csas-manage/audit/retrial\",\n" +
                "    \"/oes-csas-manage/statistics/aisleYetAuditStatistics\",\n" +
                "    \"/oes-csas-manage/ip/download\",\n" +
                "    \"/oes-csas/menu/riskTagCheck\",\n" +
                "    \"/oes-csas-manage/assetRiskInfo/saveOrUpdate\",\n" +
                "    \"/oes-csas-manage/assetHighRisk/download\",\n" +
                "    \"/oes-csas-manage/shieldType/list\",\n" +
                "    \"/oes-csas-manage/detail/outer-url/mediaLinks\",\n" +
                "    \"/oes-csas-manage/ip/template\",\n" +
                "    \"/oes-csas-manage/aisleUser/save\",\n" +
                "    \"/oes-csas-manage/uploadUser/list\",\n" +
                "    \"/oes-csas-manage/announcement/remove\",\n" +
                "    \"/oes-content-label/redis/list-enum-values\",\n" +
                "    \"/oes-csas/menu/query-batch\",\n" +
                "    \"/oes-csas-words/syncShield/getList\",\n" +
                "    \"/oes-csas-manage/check-field-conf/list\",\n" +
                "    \"/oes-csas-manage/statisticsExcel/shieldDataStatisticsExcel\",\n" +
                "    \"/oes-csas-manage/aia-task/backCheckList\",\n" +
                "    \"/oes-csas-manage/remarkPhrase/save\",\n" +
                "    \"/oes-csas-manage/aisleUser/list\",\n" +
                "    \"/oes-csas/menu/accountRiskLabel\",\n" +
                "    \"/oes-csas-manage/aisleKeyword/upload\",\n" +
                "    \"/oes-csas-manage/label/remove\",\n" +
                "    \"/oes-csas-management/access/announcementSystem\",\n" +
                "    \"/oes-csas-manage/user/risk/grade/delRiskLabel\",\n" +
                "    \"/oes-csas-manage/group/delAccount\",\n" +
                "    \"/oes-csas-manage/statistics/tableHead\",\n" +
                "    \"/oes-csas-manage/aisleUser/update\",\n" +
                "    \"/oes-csas-manage/fieldCfg/update\",\n" +
                "    \"/oes-csas/menu/offlineContent\",\n" +
                "    \"/oes-csas-manage/detail/recommendInit\",\n" +
                "    \"/oes-csas-manage/assetRiskInfo/list\",\n" +
                "    \"/oes-csas/menu/query-contentMedia\",\n" +
                "    \"/oes-csas-manage/dis/effectDisAisle\",\n" +
                "    \"/oes-csas-ai/audit/result/ducp\",\n" +
                "    \"/oes-csas-manage/label/refresh\",\n" +
                "    \"/oes-csas-words/syncShield/delete\",\n" +
                "    \"/oes-csas/menu/aisle/ip\",\n" +
                "    \"/oes-csas-words/syncShield/getInfo\",\n" +
                "    \"/oes-csas-manage/aisleAi/update\",\n" +
                "    \"/oes-csas-manage/audit-task/delTask\",\n" +
                "    \"/oes-csas/menu/aisle/aisle-keyword\",\n" +
                "    \"/oes-csas-manage/statistics/auditLinkStatistics\",\n" +
                "    \"/oes-csas-manage/configuration/queryElement\",\n" +
                "    \"/oes-csas/menu/query-inter\",\n" +
                "    \"/oes-csas/menu/sys/cfg\",\n" +
                "    \"/oes-csas/menu/words/words-source\",\n" +
                "    \"/oes-csas/menu/aisle/mapResult\",\n" +
                "    \"/oes-csas-manage/ip/delete\",\n" +
                "    \"/oes-csas-manage/log/collect/list\",\n" +
                "    \"/oes-csas/menu/bigScreen-bcHistory\",\n" +
                "    \"/oes-csas-manage/log/outer-url/selectRedisData\",\n" +
                "    \"/oes-csas-manage/audit/keep\",\n" +
                "    \"/oes-csas-manage/statistics/auditStatistics\",\n" +
                "    \"/oes-csas-manage/aisle/allAuthAisle\",\n" +
                "    \"/oes-csas-manage/dis/delDisAisle\",\n" +
                "    \"/oes-content-imagetext/img-text/get\",\n" +
                "    \"/oes-csas-manage/aisleKeyword/refresh\",\n" +
                "    \"/oes-csas-words/wordSource/remove\",\n" +
                "    \"/oes-csas-manage/aisle/authenticationAisleList\",\n" +
                "    \"/oes-csas/menu/query-all\",\n" +
                "    \"/oes-csas/menu/statistics/time\",\n" +
                "    \"/oes-csas-manage/announcement/list\",\n" +
                "    \"/oes-csas-manage/uploadUser/template\",\n" +
                "    \"/oes-csas-manage/aisleAi/upload\",\n" +
                "    \"/oes-csas/menu/shield/shield-manage\",\n" +
                "    \"/oes-csas-manage/check-field-conf/update\",\n" +
                "    \"/oes-csas-manage/fieldCfg/refresh\",\n" +
                "    \"/oes-csas-words/shield/shieldById\",\n" +
                "    \"/oes-csas-manage/audit-task/updateTask\",\n" +
                "    \"/oes-csas-manage/aisle/reset\",\n" +
                "    \"/oes-csas/menu/check-ai/audit\",\n" +
                "    \"/oes-csas/menu/bigScreen-exportTaskQuery\",\n" +
                "    \"/oes-csas-words/sensitive/refresh\",\n" +
                "    \"/oes-csas-words/shield/treeList\",\n" +
                "    \"/oes-csas-manage/audit-task/fetch\",\n" +
                "    \"/oes-csas-manage/detail/violationsList\",\n" +
                "    \"/oes-csas-manage/statisticsExcel/auditLinkStatisticsExcel\",\n" +
                "    \"/oes-csas-manage/dis/list\",\n" +
                "    \"/oes-csas/menu/safe-risk-label/programRiskTagPot\",\n" +
                "    \"/oes-csas-manage/aisleUser/get\",\n" +
                "    \"/oes-csas-manage/check/report\",\n" +
                "    \"/oes-csas-manage/audit-task/getTaskStatistical\",\n" +
                "    \"/oes-csas-manage/uploadUser/refresh\",\n" +
                "    \"/oes-csas/menu/query\",\n" +
                "    \"/oes-csas-manage/detail/outer-url/getBroadcastResult\",\n" +
                "    \"/oes-csas-manage/user/risk/grade/sync\",\n" +
                "    \"/oes-csas-manage/user/risk/grade/treeList\",\n" +
                "    \"/oes-csas-manage/detail/getAssetButton\",\n" +
                "    \"/oes-csas-manage/uploadUser/upload\",\n" +
                "    \"/oes-csas-manage/assetRiskInfo/confirmStats\",\n" +
                "    \"/oes-csas-words/wordGroup/listName\",\n" +
                "    \"/oes-csas-manage/audit/bigAssetAuditLog/export\",\n" +
                "    \"/oes-csas-manage/remarkPhrase/remove\",\n" +
                "    \"/oes-csas-manage/riskLabel/list\",\n" +
                "    \"/oes-csas-manage/sysDictType/remove\",\n" +
                "    \"/oes-csas/menu/query-bigScreen\",\n" +
                "    \"/oes-csas-manage/audit/submit\",\n" +
                "    \"/oes-csas/menu/query-del\",\n" +
                "    \"/oes-csas-manage/statistics/weekTableHead\",\n" +
                "    \"/oes-csas-manage/statistics/shieldDataStatistics\",\n" +
                "    \"/oes-csas-manage/label/list\",\n" +
                "    \"/oes-csas-manage/syncUserRiskLabel/list\",\n" +
                "    \"/oes-csas-manage/aia-task/backCheckListExport\",\n" +
                "    \"/oes-csas-manage/detail/tipUser\",\n" +
                "    \"/oes-csas-manage/sysDictType/save\",\n" +
                "    \"/oes-csas/menu/query-content\",\n" +
                "    \"/oes-csas-manage/detail/homeList\",\n" +
                "    \"/oes-csas-manage/group/account/pageList\",\n" +
                "    \"/oes-csas-manage/userRisk/list\",\n" +
                "    \"/oes-csas-manage/check/remove\",\n" +
                "    \"/oes-csas-manage/user/risk/grade/batchImport\",\n" +
                "    \"/oes-csas-manage/aisleUser/upload\",\n" +
                "    \"/oes-csas-manage/reviewLibrary/download\",\n" +
                "    \"/oes-csas-manage/announcement/save\",\n" +
                "    \"/oes-csas/menu/statistics/daily\",\n" +
                "    \"/oes-csas-manage/check-field-conf/getInfo\",\n" +
                "    \"/oes-csas-manage/riskLabelGrade/saveOrUpdate\",\n" +
                "    \"/oes-csas-manage/remarkPhrase/list\",\n" +
                "    \"/oes-csas-manage/aisle/list\",\n" +
                "    \"/oes-csas-words/syncShield/save\",\n" +
                "    \"/oes-csas-manage/aisleUser/refresh\",\n" +
                "    \"/oes-csas-manage/riskLabel/delRiskLabel\",\n" +
                "    \"/oes-csas/menu/sys/ann\",\n" +
                "    \"/oes-csas-manage/audit/bigAuditLog/export\",\n" +
                "    \"/oes-csas-manage/detail/recommendTags\",\n" +
                "    \"/oes-csas-manage/detail/allUserRiskLabels\",\n" +
                "    \"/oes-csas/menu/safe-risk-label/programRiskTagSyn\",\n" +
                "    \"/oes-csas-manage/offLineTask/retry\",\n" +
                "    \"/oes-csas/menu/index\",\n" +
                "    \"/oes-csas-manage/aia-record/getOne\",\n" +
                "    \"/oes-csas-manage/aisle/listByAI\",\n" +
                "    \"/oes-csas-manage/aisleKeyword/update\",\n" +
                "    \"/oes-csas/menu/statistics/shield\",\n" +
                "    \"/oes-csas-manage/sysDictData/list\",\n" +
                "    \"/oes-csas-ai/audit/result/image\",\n" +
                "    \"/oes-csas-manage/aia-record/pageList\",\n" +
                "    \"/oes-csas-manage/assetRiskInfo/batchImport\",\n" +
                "    \"/oes-csas-manage/audit-task/addTask\",\n" +
                "    \"/oes-csas-manage/group/saveAuditGroup\",\n" +
                "    \"/oes-csas/menu/aisle/audit-remark\",\n" +
                "    \"/oes-csas/menu/aisle/{name}/audit-remark\",\n" +
                "    \"/oes-csas-words/word/upload\",\n" +
                "    \"/oes-csas-manage/reviewLibrary/template\",\n" +
                "    \"/oes-csas/menu/aia-intelligent/createTask\",\n" +
                "    \"/oes-csas/menu/statistics/weekly\",\n" +
                "    \"/oes-csas-manage/detail/showFromAisleInfo\",\n" +
                "    \"/oes-csas-manage/group/saveAccount\",\n" +
                "    \"/oes-csas-words/syncShield/update\",\n" +
                "    \"/oes-csas/menu/sys/aiaquery\",\n" +
                "    \"/oes-csas-manage/detail/outer-url/getAisleVideoTypeInfo\",\n" +
                "    \"/oes-csas/menu/audit-task/auditTeamManage\",\n" +
                "    \"/oes-csas-manage/remarkPhrase/refresh/{name}\"\n" +
                "]";
        URLS = JSONUtils.jsonToObject(urls, new TypeReference<List<String>>() {
        });


    }

    public static void main(String[] args) {
      String  str2="/oes-csas/menu/sys/aiaquery";
      String  str1="/oes-csas/menu/aisle/fgfhfh/audit-remark";
      String  str="/oes-csas-manage/remarkPhrase/refresh/1111";
//        test02(str2);
//      test01(str2);
//      test03(str);
      test04(str);


//        System.out.println(havePerm(URLS, str));


    }

    public  static  void  test01(String str){
        long l = System.nanoTime();
        boolean contains = URLS.contains(str);
        if(contains){
            System.out.println("str = " + str);
        }
        long s = System.nanoTime();

        System.out.println("test01  =="+(s-l)+"  ns");
    }

    public  static  void  test02(String str){
        long l = System.nanoTime();

        for (String url : URLS) {
            if(url.equals(str)){
                System.out.println("str = " + url);
                break;
            }
        }

        long s = System.nanoTime();

        System.out.println("test02  =="+(s-l)+"  ns");
    }


    public  static  void  test03(String str){
        long l = System.nanoTime();
        for (String url : URLS) {
            if(isSpecialChar(url)){
                boolean specialChar = isSpecialChar(url, str);
                if(specialChar){
                    System.out.println(str);
                }
            }else {
                boolean equals = url.equals(str);
                if(equals){
                    System.out.println(str);
                }
            }
        }

        long s = System.nanoTime();

        System.out.println("test03  ="+(s-l)+"  ns");
    }


    public  static  void  test04(String str){
        long l = System.currentTimeMillis();
        for (String url : URLS) {
            if(isSpecialChar(url)){
                boolean specialChar = isSpecialChar(url, str);
                if(specialChar){
                    System.out.println(url);
                }
            }else {
                boolean equals = url.equals(str);
                if(equals){
                    System.out.println(url);
                }
            }
        }

        long s = System.currentTimeMillis();

        System.out.println("test04  ="+(s-l)+"  ms");
    }


    public static boolean isSpecialChar(String str) {
        String regEx = "[{}]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public  static  boolean  havePerm(List<String> urls, String url1){
        for (String url : urls) {
            if(isSpecialChar(url)){
                boolean specialChar = isSpecialChar(url, url1);
                if(specialChar){
                    System.out.println(url);
                    return true;
                }
            }else {
                boolean equals = url.equals(url1);
                if(equals){
                    System.out.println(url);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isSpecialChar(String url1,String url2){
        //  /oes-csas/menu/aisle/fgfhfh/audit-remark
        //  /oes-csas/menu/aisle/{name}/audit-remark
        boolean flag=true;
        String[] split1 = url1.split("/");
        String[] split2 = url2.split("/");
        if(split1.length != split2.length){
            return false;
        }

        for (int i = 0; i < split1.length; i++) {
            String s = split1[i];
            if(isSpecialChar(s)){
                continue;
            }
            if(!s.equals(split2[i])){
                flag=false;
                break;
            }
        }
        return flag;
    }

}
