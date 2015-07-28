package test.spring.web.action;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import test.spring.common.BizUtil;
import test.spring.exception.BusinessException;
import test.spring.web.ErrorCode;

public abstract class AbstractAction {

	/** 默认入参数组最大长度 */
	public static final int DEFAULT_LIST_MAX_SIZE = 200;

	/**
	 * @title: checkParamBlank
	 * @description: 检查参数是否存在空(null、""、" ")，如果有则抛出参数为空异常
	 * @param args
	 * @throws BusinessException
	 */
	public void checkParamBlank(Object... args) throws BusinessException {
		if (BizUtil.isExistBlank(args)) {
			throw new BusinessException(ErrorCode.PARAMETER_ERROR, "必填参数为空");
		}
	}

	/**
	 * @title: checkParamAllBlank
	 * @description: 检查参数是否全部为空(null、""、" ")，如果全部为空则抛出参数全部为空异常
	 * @param args
	 * @throws BusinessException
	 */
	public void checkParamAllBlank(Object... args) throws BusinessException {
		if (BizUtil.isAllBlank(args)) {
			throw new BusinessException(ErrorCode.PARAMETER_ERROR, "必填参数为空");
		}
	}

	/**
	 * @title: checkNonPositiveParam
	 * @description: 检查参数是否是正数
	 * @param args
	 * @throws BusinessException
	 */
	public void checkNonPositiveParam(Number... args) throws BusinessException {
		if (BizUtil.isExistNonPositive(args)) {
			throw new BusinessException(ErrorCode.PARAMETER_ERROR, "参数存在无效值");
		}
	}

	/** 
	 * @title: checkPaginationParam
	 * @description: 检查分页参数是否有效
	 * @param offset
	 * @param count void
	 * @throws BusinessException 
	 */ 
	public void checkPaginationParam(int offset, int count) throws BusinessException {
		if (offset < 0 || count <= 0 || count > DEFAULT_LIST_MAX_SIZE) {
			throw new BusinessException(ErrorCode.PARAMETER_ERROR, "分页参数无效");
		}
	}

	/**
	 * @title: checkListParamIsNumeric
	 * @description: 判断数组字串是否是数字类型
	 * @param listStr
	 * @throws BusinessException
	 *             void
	 */
	public void checkListParamIsNumeric(String listStr) throws BusinessException {
		if (StringUtils.isBlank(listStr) || listStr.split(",").length <= 0) {
			return;
		}
		for (String str : listStr.split(",")) {
			if (!StringUtils.isNumeric(str)) {
				throw new BusinessException(ErrorCode.PARAMETER_ERROR, "参数存在无效值");
			}
		}
	}

	/**
	 * @title: checkListParam
	 * @description: 检查list参数是否有效
	 * @param listStr
	 * @throws BusinessException
	 */
	public void checkListParam(String listStr) throws BusinessException {
		if (StringUtils.isBlank(listStr) || listStr.split(",").length <= 0) {
			throw new BusinessException(ErrorCode.PARAMETER_ERROR, "list参数为空");
		}
		if (listStr.split(",").length > DEFAULT_LIST_MAX_SIZE) {
			throw new BusinessException(ErrorCode.PARAMETER_ERROR, "list参数超长");
		}
	}

	/**
	 * @title: checkListParam
	 * @description: 检查list参数是否有效
	 * @param listStr
	 * @param maxSize
	 * @throws BusinessException
	 */
	public void checkListParam(String listStr, int maxSize) throws BusinessException {
		if (StringUtils.isBlank(listStr) || listStr.split(",").length <= 0) {
			throw new BusinessException(ErrorCode.PARAMETER_ERROR, "list参数为空");
		}
		if (listStr.split(",").length > maxSize) {
			throw new BusinessException(ErrorCode.PARAMETER_ERROR, "list参数超长");
		}
	}

	/**
	 * @title: checkListParamSize
	 * @description: 检查list参数是否有效
	 * @param list
	 * @param maxSize 最大长度限制
	 * @throws BusinessException
	 */
	public <T> void checkListParam(List<T> list, int maxSize) throws BusinessException {
		if (CollectionUtils.isEmpty(list)) {
			throw new BusinessException(ErrorCode.PARAMETER_ERROR, "list参数为空");
		}
		if (list.size() > maxSize) {
			throw new BusinessException(ErrorCode.PARAMETER_ERROR, "list参数超长");
		}
	}
	
	/** 
	 * @title: isEmpty
	 * @description: 判断是否为空
	 * @param str
	 * @return boolean
	 */ 
	public boolean isEmpty(String str) {
		return StringUtils.isEmpty(str);
	}
}