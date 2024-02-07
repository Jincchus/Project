import { axiClient, axiValid } from "@/utils/globalAxios";
import { URLCONST } from "@/constants/APIconst";
import { AUTH_CONST } from "@/constants/authConst";

/**
 * 로그인 한다. jwt access(response header),refresh(cookie) token을 받는다.
 * @param loginInfo : {"email","password"}
 * @returns accessToken : string
 */
export const login = async (loginInfo) => {
  return await axiClient
    .post(URLCONST.LOGIN, loginInfo, {
      headers: { "Content-type": "application/json" },
      withCredentials: true,
    })
    .then((res) => res.headers.get(AUTH_CONST.AUTH_HEADER));
};
const authHeader = AUTH_CONST.AUTH_HEADER;
const bearer = AUTH_CONST.AUTH_BEARER
export const logout = async (accessToken) => {
  return await axiValid.get(URLCONST.LOGOUT, {
    headers: {
        Authorization: bearer + accessToken,
        'Content-type': 'application/json; charset=UTF-8',},
  });
};
