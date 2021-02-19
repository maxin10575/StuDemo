package com.mx.example.demo;/*
package com.example.demo;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.secframe.client.SecframeClient;
import com.ai.secframe.common.service.interfaces.ISecframeFSV;
import com.ai.secframe.sysmgr.bo.BOSecFunctionBean;
import com.ai.secframe.sysmgr.bo.BOSecFunctionEngine;
import com.ai.secframe.sysmgr.bo.BOSecRoleBean;
import com.ai.secframe.sysmgr.ivalues.IBOSecRoleGrantValue;
import com.ai.secframe.sysmgr.ivalues.IBOSecRoleValue;
import com.asiainfo.openplatform.opr.ability.bo.AbilityAccProtocolBean;
import com.asiainfo.openplatform.opr.ability.bo.AbilityAppInfoBean;
import com.asiainfo.openplatform.opr.ability.bo.AbilityBaseBean;
import com.asiainfo.openplatform.opr.ability.bo.AbilityDirectoryBean;
import com.asiainfo.openplatform.opr.ability.bo.AbilityEsbDomainBean;
import com.asiainfo.openplatform.opr.ability.bo.AbilityExtBean;
import com.asiainfo.openplatform.opr.ability.bo.AbilityInfoBean;
import com.asiainfo.openplatform.opr.ability.bo.AbilityParamBean;
import com.asiainfo.openplatform.opr.ability.dao.interfaces.IAbilityDAO;
import com.asiainfo.openplatform.opr.ability.ivalues.IAbilityAppInfoValue;
import com.asiainfo.openplatform.opr.ability.ivalues.IAbilityBaseValue;
import com.asiainfo.openplatform.opr.ability.ivalues.IAbilityExtValue;
import com.asiainfo.openplatform.opr.ability.ivalues.IAbilityInvokeLogValue;
import com.asiainfo.openplatform.opr.ability.ivalues.IQAbilityInvokedSumValue;
import com.asiainfo.openplatform.opr.ability.ivalues.IQAppAbilityInvokedSumValue;
import com.asiainfo.openplatform.opr.ability.service.interfaces.IAbilitySV;
import com.asiainfo.openplatform.opr.app.ivalues.IAopUserInfoValue;
import com.asiainfo.openplatform.opr.approval.service.interfaces.IApprovalSheetSV;
import com.asiainfo.openplatform.opr.common.ApprovalTypeCodeEnums;
import com.asiainfo.openplatform.opr.common.ApprovalTypeEnums;
import com.asiainfo.openplatform.opr.common.util.DateUtil;
import com.asiainfo.openplatform.opr.service.bo.AopAbilityServiceRefBean;
import com.asiainfo.openplatform.opr.service.service.interfaces.IAopAbilityServiceRefSV;
import com.asiainfo.openplatform.opr.service.service.interfaces.IAopServiceAuthSV;
import com.asiainfo.openplatform.opr.todotask.Constants;
import com.asiainfo.openplatform.opr.todotask.ivalues.IOperatorTaskValue;
import com.asiainfo.openplatform.opr.todotask.service.interfaces.ITodoTaskInfoSV;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AbilitySVImpl
        implements IAbilitySV
{
    private static final transient Log log = LogFactory.getLog(AbilitySVImpl.class);

    IAbilityDAO dao = (IAbilityDAO)ServiceFactory.getService(IAbilityDAO.class);

    IAopServiceAuthSV authService = (IAopServiceAuthSV)ServiceFactory.getService(IAopServiceAuthSV.class);

    IAopAbilityServiceRefSV serviceRef = (IAopAbilityServiceRefSV)ServiceFactory.getService(IAopAbilityServiceRefSV.class);

    public long addAbility(IAbilityBaseValue abilityBaseValue, IAbilityExtValue abilityExtValue, List<AbilityParamBean> abilityParamValues, List<AbilityAccProtocolBean> protocols, AbilityDirectoryBean direc, Map<String, String> accMap)
            throws Exception
    {
        String SERVICEID = (String)accMap.get("SERVICEID");
        AopAbilityServiceRefBean[] srvRefs = null;
        String flag = "add";
        long taskid = 0L;
        if (abilityBaseValue.getAbilityId() != 0L)
        {
            log.debug("satrt AbilitySVImpl addAbility  ---update");
            flag = "update";
            long aId = abilityBaseValue.getAbilityId();
            taskid = aId;

            AbilityBaseBean base = this.dao.getAbilityBaseById(aId);
            abilityBaseValue.setCreateTime(base.getCreateTime());
            abilityBaseValue.setCreator(base.getCreator());
            this.dao.updateOrDelBase(base);
            String abltId = String.valueOf(aId);
            String nodeType = "1";

            AbilityExtBean[] exts = this.dao.getExt(abltId);
            AbilityDirectoryBean[] dires = this.dao.getAbilityDir(abltId, nodeType);
            AbilityAccProtocolBean[] pros = this.dao.getProtocol(abltId);
            srvRefs = this.dao.getSrvRef(abltId);

            if (exts.length > 0) {
                exts[0].delete();
                this.dao.addExt(exts[0]);
            }
            if (dires.length > 0) {
                direc.setId(dires[0].getId());
                dires[0].delete();
                this.dao.addAbilityDir(dires[0]);
            }

            if (pros.length > 0) {
                for (int i = 0; i < pros.length; i++) {
                    pros[i].delete();
                }

                this.dao.updateOrDelProtocol(pros);
            }
        }

        this.dao.addBase(abilityBaseValue);
        Long aId = Long.valueOf(abilityBaseValue.getAbilityId());
        abilityExtValue.setAbilityId(aId.longValue());

        this.dao.addExt(abilityExtValue);
        this.dao.addProtocol(protocols, aId.longValue());

        direc.setNodeId(aId.longValue());
        this.dao.addAbilityDir(direc);

        AopAbilityServiceRefBean aopAbilityServiceRef = null;
        if ((srvRefs != null) && (srvRefs.length > 0)) {
            aopAbilityServiceRef = srvRefs[0];
            aopAbilityServiceRef.setServiceId(Long.valueOf(SERVICEID).longValue());
        }
        else {
            aopAbilityServiceRef = new AopAbilityServiceRefBean();
            aopAbilityServiceRef.setAbilityId(aId.longValue());
            aopAbilityServiceRef.setServiceId(Long.valueOf(SERVICEID).longValue());
        }

        this.serviceRef.addAopAbilityServiceRef(aopAbilityServiceRef);
        String abilitName = abilityBaseValue.getAbilityName();

        IApprovalSheetSV approvalService = (IApprovalSheetSV)ServiceFactory.getService(IApprovalSheetSV.class);
        if ("update".equalsIgnoreCase(flag))
        {
            approvalService.initNewApprovalSheet(aId + "", ApprovalTypeCodeEnums.APPROVAL_TYPE_ABILITY_CHANGE.getType_code(), Long.valueOf(abilityBaseValue.getCreator()).longValue(), Constants.getAbltEditTitle(abilitName));
        }
        else {
            approvalService.initNewApprovalSheet(aId + "", ApprovalTypeCodeEnums.APPROVAL_TYPE_ABILITY.getType_code(), Long.valueOf(abilityBaseValue.getCreator()).longValue(), Constants.getAbltCreateTitle(abilitName));
        }

        return aId.longValue();
    }

    public AbilityDirectoryBean[] getNodes(String id, String pId)
            throws Exception
    {
        AbilityDirectoryBean[] abilityDirectorys = this.dao.getNodes(id, pId);
        return abilityDirectorys;
    }

    public List<Map<String, Object>> getChildList(String pid, List<AbilityDirectoryBean> arrTreeChild)
            throws Exception
    {
        List arrChildTree = new ArrayList();
        Map map = null;

        for (AbilityDirectoryBean treeT : arrTreeChild) {
            map = new HashMap();
            map.put("id", Long.valueOf(treeT.getId()));

            map.put("name", treeT.getNodeName());
            map.put("nodeType", treeT.getNodeType());
            map.put("nodeId", Long.valueOf(treeT.getNodeId()));
            map.put("remarks", treeT.getRemarks());

            String cId = String.valueOf(treeT.getId());
            AbilityDirectoryBean[] ChTreeNodes = getNodes("", cId);
            if ((ChTreeNodes != null) && (ChTreeNodes.length > 0))
            {
                map.put("children", getChildList(cId, Arrays.asList(ChTreeNodes)));
            }
            else if ((treeT.getNodeType() != null) && ("2".equals(treeT.getNodeType())))
            {
                map.put("isParent", Boolean.valueOf(true));
            }
            arrChildTree.add(map);
        }

        return arrChildTree;
    }

    public AbilityDirectoryBean[] getAllAbilityNodes(String nodeType)
            throws Exception
    {
        String otherId = "10";
        AbilityDirectoryBean[] abilityNodes = this.dao.getAllAbilityNodes(nodeType, otherId);

        return abilityNodes;
    }

    public void deleteAbilityRef(String id, String pId)
            throws Exception
    {
        AbilityDirectoryBean node = this.dao.getNodeById(id);
        String otherId = "10";
        node.setParentId(Long.valueOf(otherId).longValue());
        this.dao.updateOrDelDirecInfo(node);
    }

    public void deleteDirec(String id)
            throws Exception
    {
        String otherId = "10";
        AbilityDirectoryBean[] abilityNodes = this.dao.getNodes("", id);
        if ((abilityNodes != null) && (abilityNodes.length > 0)) {
            int length = abilityNodes.length;
            for (int i = 0; i < length; i++) {
                abilityNodes[i].setParentId(Long.valueOf(otherId).longValue());
            }
        }

        this.dao.updateAbilityPid(abilityNodes);

        AbilityDirectoryBean direcNode = this.dao.getNodeById(id);
        direcNode.delete();
        this.dao.updateOrDelDirecInfo(direcNode);
    }

    public void saveDrag(AbilityDirectoryBean[] ad)
            throws Exception
    {
        if ((ad != null) && (ad.length > 0)) {
            StringBuffer condIds = new StringBuffer();
            for (int i = 0; i < ad.length; i++) {
                long id = ad[i].getId();
                condIds.append(id);
                if (i < ad.length - 1) {
                    condIds.append(",");
                }
            }
            String ids = condIds.toString();
            log.debug(ids);
            AbilityDirectoryBean[] dragNodes = this.dao.getDragNodesByIds(ids);

            for (int i = 0; i < dragNodes.length; i++) {
                for (int j = 0; j < ad.length; j++) {
                    if (dragNodes[i].getId() == ad[j].getId()) {
                        dragNodes[i].setParentId(ad[j].getParentId());
                    }
                }
            }

            this.dao.saveNodes(dragNodes);
        }
    }

    public void addDirInfo(AbilityDirectoryBean ad)
            throws Exception
    {
        this.dao.addDirInfo(ad);
    }

    public void updateDirectory(AbilityDirectoryBean ad)
            throws Exception
    {
        long id = ad.getId();
        AbilityDirectoryBean direcNode = this.dao.getNodeById(String.valueOf(id));
        direcNode.setNodeName(ad.getNodeName());
        direcNode.setRemarks(ad.getRemarks());
        this.dao.updateOrDelDirecInfo(direcNode);
    }

    public AbilityEsbDomainBean[] getAllDomains()
            throws Exception
    {
        AbilityEsbDomainBean[] esb = this.dao.getAllDomains();
        return esb;
    }

    public int getParamCount(String abilityId, String paramType)
            throws Exception
    {
        return this.dao.getParamCount(abilityId, paramType);
    }

    public AbilityParamBean[] getParams(String abilityId, String paramType, int start, int end)
            throws Exception
    {
        return this.dao.getParams(abilityId, paramType, start, end);
    }

    public AbilityInfoBean getAbilityInfoById(long abilityId)
            throws Exception
    {
        return this.dao.getAbilityInfoById(abilityId);
    }

    public void updateAbilityBase(AbilityBaseBean abilityBaseBean)
            throws Exception
    {
        long aId = abilityBaseBean.getAbilityId();
        AbilityBaseBean abilitybase = this.dao.getAbilityBaseById(aId);
        if (StringUtils.isNotBlank(abilityBaseBean.getAbilityName())) {
            abilitybase.setAbilityName(abilityBaseBean.getAbilityName());
        }
        if (StringUtils.isNotBlank(abilityBaseBean.getAbilitySketch())) {
            abilitybase.setAbilitySketch(abilityBaseBean.getAbilitySketch());
        }
        if (StringUtils.isNotBlank(abilityBaseBean.getAbilitySort())) {
            abilitybase.setAbilitySort(abilityBaseBean.getAbilitySort());
        }
        if (abilityBaseBean.getQuotaNums() != 0L)
        {
            abilitybase.setQuotaNums(abilityBaseBean.getQuotaNums());
        }
        if (abilityBaseBean.getFlowNums() != 0L) {
            abilitybase.setFlowNums(abilityBaseBean.getFlowNums());
        }
        if (StringUtils.isNotBlank(abilityBaseBean.getEsbDomainId())) {
            abilitybase.setEsbDomainId(abilityBaseBean.getEsbDomainId());
        }
        if (StringUtils.isNotBlank(abilityBaseBean.getAbilityStatus())) {
            abilitybase.setAbilityStatus(abilityBaseBean.getAbilityStatus());
        }
        this.dao.updateOrDelAbility(abilitybase);
    }

    public AbilityDirectoryBean[] queryNodes(AbilityDirectoryBean abilityDirectoryBean)
            throws Exception
    {
        return this.dao.queryNodes(abilityDirectoryBean);
    }

    public void updateParams(List<AbilityParamBean> abilityParams, String aId)
            throws Exception
    {
        int start = 0;
        int end = 0;
        String paramType = null;
        AbilityParamBean[] params = getParams(aId, paramType, start, end);
        this.dao.delParams(params, aId);
        this.dao.addParam(abilityParams, Long.valueOf(aId));
    }

    public AbilityAppInfoBean[] getApps(String abilityId, String num, int start, int end)
            throws Exception
    {
        return this.dao.getApps(abilityId, num, start, end);
    }

    public IQAbilityInvokedSumValue[] qryAbilityInvokedSum(String abilityName, String startTime, String endTime, int startNum, int endNum)
            throws Exception
    {
        IAbilityDAO dao = (IAbilityDAO)ServiceFactory.getService(IAbilityDAO.class);

        return dao.qryAbilityInvokedSum(abilityName, startTime, endTime, startNum, endNum);
    }

    public int getAbilityInvokedSumCount(String abilityName, String startTime, String endTime)
            throws Exception
    {
        IAbilityDAO dao = (IAbilityDAO)ServiceFactory.getService(IAbilityDAO.class);

        return dao.getAbilityInvokedSumCount(abilityName, startTime, endTime);
    }

    public Map<String, IAbilityAppInfoValue> qryAbltAppInfoMap()
            throws Exception
    {
        Map rtnMap = new HashMap();
        IAbilityAppInfoValue[] infos = this.dao.qryAllAbltInfo();
        for (IAbilityAppInfoValue info : infos) {
            rtnMap.put(String.valueOf(info.getAbilityId()), info);
        }
        return rtnMap;
    }

    public List<IAbilityInvokeLogValue> qryInvokeLog(String abltId, String appId, Timestamp startDate, Timestamp endDate, int startIndex, int endIndex)
            throws Exception
    {
        IAbilityDAO dao = (IAbilityDAO)ServiceFactory.getService(IAbilityDAO.class);

        int monthSpace = DateUtil.getMonthSpace(startDate.getTime(), endDate.getTime());

        List rstList = new ArrayList();
        Timestamp ts = startDate;
        int totalCount = 0;

        for (int i = 0; i < monthSpace + 1; i++) {
            ts = new Timestamp(DateUtil.getNowInAfterMonth(startDate.getTime(), i));

            IAbilityInvokeLogValue[] vals = dao.qryInvokeLog(abltId, appId, startDate, endDate, startIndex, endIndex, ts);

            for (IAbilityInvokeLogValue val : vals) {
                rstList.add(val);
            }

            totalCount = vals.length;
            if (totalCount != endIndex - startIndex + 1) {
                endIndex = endIndex - startIndex + 1 - totalCount;
                startIndex = 1;
            } else {
                return rstList;
            }
        }
        return rstList;
    }

    public int qryInvokeLogCount(String abltId, String appId, Timestamp startDate, Timestamp endDate)
            throws Exception
    {
        IAbilityDAO dao = (IAbilityDAO)ServiceFactory.getService(IAbilityDAO.class);

        int monthSpace = DateUtil.getMonthSpace(startDate.getTime(), endDate.getTime());

        int totleCount = 0;
        Timestamp ts = startDate;

        for (int i = 0; i < monthSpace + 1; i++) {
            ts = new Timestamp(DateUtil.getNowInAfterMonth(startDate.getTime(), i));

            int count = dao.qryInvokeLogCount(abltId, appId, startDate, endDate, ts);

            totleCount += count;
        }
        return totleCount;
    }

    public int queryAbilityByCode(String code, String abilityId)
            throws Exception
    {
        log.debug("enter AbilitySVImpl queryAbilityByCode code:" + code);
        int num = this.dao.queryAbilityByCode(code, abilityId);
        log.debug("exit AbilitySVImpl queryAbilityByCode num:" + num);
        return num;
    }

    public void changeTaskInvalid(long taskid)
            throws Exception
    {
        ITodoTaskInfoSV todosv = (ITodoTaskInfoSV)ServiceFactory.getService(ITodoTaskInfoSV.class);

        String[] type = { ApprovalTypeEnums.APPROVAL_TYPE_AOP.getType_value(), ApprovalTypeEnums.APPROVAL_TYPE_AOP_CHANGE.getType_value() };

        IOperatorTaskValue[] qryOprTsk = todosv.qryOprTsk(String.valueOf(taskid), type);

        if (qryOprTsk.length > 0) {
            List aidList = new ArrayList();
            for (int i = 0; i < qryOprTsk.length; i++) {
                if ("2".equals(qryOprTsk[i].getStatus())) {
                    long tid = qryOprTsk[i].getTid();
                    aidList.add(Long.valueOf(tid));
                }
            }
            int length = aidList.size();
            if (length > 0) {
                Long[] tids = new Long[length];
                for (int i = 0; i < length; i++) {
                    tids[i] = ((Long)aidList.get(i));
                }
                todosv.upOprTskStatus(tids, "3");
            }
        }
    }

    public long getInitAbilityCode()
            throws Exception
    {
        return this.dao.getInitAbilityCode();
    }

    public void saveDirAndRoleFunction(AbilityDirectoryBean directoryBean) throws Exception
    {
        addDirInfo(directoryBean);
        long id = directoryBean.getId();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strNow = sdf.format(date);
        Timestamp validDate = Timestamp.valueOf(strNow);

        BOSecRoleBean secRole = new BOSecRoleBean();
        secRole.setRoleName(directoryBean.getNodeName());
        secRole.setRoleType(2);
        secRole.setDomainId(1);
        secRole.setNotes(directoryBean.getNodeName());
        secRole.setCreateDate(validDate);
        secRole.setValidDate(validDate);

        IBOSecRoleValue role = secRole;
        this.dao.saveRole(role);

        long newId = BOSecFunctionEngine.getNewId().longValue();
        BOSecFunctionBean secFunc = new BOSecFunctionBean();
        secFunc.setFuncId(newId);
        secFunc.setFuncCode("DIR");
        secFunc.setName(directoryBean.getNodeName());
        secFunc.setNotes(directoryBean.getNodeName());
        secFunc.setEntClassId(1L);
        secFunc.setState(1);
        secFunc.setStsToNew();
        secFunc.setFunSeq(1);
        secFunc.setParentId(2L);
        secFunc.setModuleType(2);
        secFunc.setDomainId(1);
        secFunc.setViewname(String.valueOf(id));

        secFunc.setFuncType("1");
        secFunc.setCreateDate(validDate);
        secFunc.setDoneDate(validDate);
        BOSecFunctionEngine.save(secFunc);

        String[] funcs = { newId + "" };

        ISecframeFSV scframeSV = (ISecframeFSV)ServiceFactory.getService(ISecframeFSV.class);
        scframeSV.saveRoleFunction(role.getRoleId(), funcs);
    }

    public void delDirAndSec(String id, String name)
            throws Exception
    {
        deleteDirec(id);

        IBOSecRoleValue[] secRoles = SecframeClient.querySecRole(name, 2L, 0L, "");
        long roleId = 0L;
        if ((secRoles != null) && (secRoles.length > 0)) {
            IBOSecRoleValue secRole = secRoles[0];
            roleId = secRole.getRoleId();

            this.dao.delSecRole(roleId);

            IBOSecRoleGrantValue[] secRoleGrants = SecframeClient.getRoleFuncValue(roleId);

            StringBuffer funcIds = new StringBuffer();
            if (secRoleGrants != null) {
                for (int i = 0; i < secRoleGrants.length; i++) {
                    long funcId = secRoleGrants[0].getEntId();

                    SecframeClient.delRoleFunction(roleId, funcId);

                    funcIds.append(funcId);
                    if (i != secRoleGrants.length - 1) {
                        funcIds.append(",");
                    }
                }
                this.dao.delSecFunction(funcIds.toString());
            }
        }
    }

    public void updateDirectoryAndSec(AbilityDirectoryBean ad, String nodeName_org)
            throws Exception
    {
        long id = ad.getId();
        AbilityDirectoryBean direcNode = this.dao.getNodeById(String.valueOf(id));
        direcNode.setNodeName(ad.getNodeName());
        direcNode.setRemarks(ad.getRemarks());
        this.dao.updateOrDelDirecInfo(direcNode);

        Map queryStr = new HashMap();
        queryStr.put("roleName", nodeName_org);
        queryStr.put("roleType", String.valueOf(2));
        BOSecRoleBean[] roleBean = this.dao.queryRole(queryStr);
        if ((roleBean != null) && (roleBean.length > 0)) {
            BOSecRoleBean bean = roleBean[0];
            bean.setRoleName(ad.getNodeName());
            this.dao.update(bean);
        }
    }

    public int qryAppAbilityInvokedCount(String abilityID, String appID, String u_ID, String startTime, String endTime, String timeType)
            throws Exception
    {
        return this.dao.qryAppAbilityInvokedCount(abilityID, appID, u_ID, startTime, endTime, timeType);
    }

    public IQAppAbilityInvokedSumValue[] qryAppAbilityInvokedSum(String abilityName, String appName, String u_account, String startTime, String endTime, int startNum, int endNum, String timeType)
            throws Exception
    {
        return this.dao.qryAppAbilityInvokedSum(abilityName, appName, u_account, startTime, endTime, startNum, endNum, timeType);
    }

    public IQAppAbilityInvokedSumValue[] qryAppAbilityInvokedSum(String abilityName, String appName, String u_account, String startTime, String endTime, String timeType)
            throws Exception
    {
        return this.dao.qryAppAbilityInvokedSum(abilityName, appName, u_account, startTime, endTime, timeType);
    }

    public IQAppAbilityInvokedSumValue[] qryAppAbilityReport(String abilityName, String appName, String uaccount, String startTime, String endTime)
            throws Exception
    {
        return this.dao.qryAppAbilityReport(abilityName, appName, uaccount, startTime, endTime);
    }

    public IAbilityBaseValue[] getabilitys() throws Exception
    {
        return this.dao.getabilitys();
    }

    public IAopUserInfoValue[] getUAccounts() throws Exception
    {
        return this.dao.getUAccounts();
    }
}
*/
