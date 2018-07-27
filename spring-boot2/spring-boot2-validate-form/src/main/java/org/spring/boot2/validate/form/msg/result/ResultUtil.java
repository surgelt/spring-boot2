package org.spring.boot2.validate.form.msg.result;

/**
 * 公共响应结果成功失败的静态方法调用
 *
 */
public class ResultUtil {

	/**
	 * return success
	 *
	 * @param data
	 * @return
	 */
	public static <T> CommonResult<T> returnSuccess(T data) {
		CommonResult<T> result = new CommonResult<T>();
		result.setCode(ErrorCodeEnum.SUCCESS.getCode());
		result.setSuccess(true);
		result.setData(data);
		result.setMessage(ErrorCodeEnum.SUCCESS.getDesc());
		return result;
	}

	/**
	 * return error
	 *
	 * @param code
	 *            error code
	 * @param msg
	 *            error message
	 * @return
	 */
	public static <T> CommonResult<T> returnError(String code, String msg) {
		CommonResult<T> result = new CommonResult<T>();
		result.setCode(code);
		// result.setData((T) "");
		result.setMessage(msg);
		return result;

	}

	/**
	 * use enum
	 *
	 * @param status
	 * @return
	 */
	public static <T> CommonResult<T> returnError(ErrorCodeEnum status) {
		return returnError(status.getCode(), status.getDesc());
	}
}