<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file ="../_header.jsp" %>
        <section id="cs">
            <div class="notice">
                 <nav>
                    <div>
                        <p>홈<span>></span>공지사항</p>
                    </div>
                </nav>
                <section class="write">
                     <aside>
                        <h2>공지사항</h2>
                        <ul>
                            <li>
                                <a href="#">전체</a>
                            </li>
                            <li>
                                <a href="#">고객서비스</a>
                            </li>
                            <li>
                                <a href="#">안전거래</a>
                            </li>
                            <li>
                                <a href="#">위해상품</a>
                            </li>
                            <li>
                                <a href="#">이벤트당첨</a>
                            </li>
                        </ul>
                    </aside>
                    <article>
                        <form action="./write.do" method ="post" enctype="multipart/form-data">
                            <input type="hidden" name="cCate" value="10">
                            <input type="hidden" name="writer" value="writer">
                            <table>
                               <tbody>
                                <tr>
                                    <td>문의제목</td>
                                    <td><input type="text" name="title" placeholder="제목을 입력하시오."></td>
                                </tr>
                                <tr>
                                    <td>문의내용</td>
                                    <td>
                                        <textarea name="content" placeholder="내용을 입력하시오."></textarea>
                                    </td>
                                </tr>
                                <tr>
		                            <td>파일</td>
		                            <td>
		                                <input type="file" name="file"/>
		                            </td>
		                        </tr>
                               </tbody>
                            </table>
                            <div>
                                <a href="./list.do" class="btnList">취소하기</a>
                                <input type="submit" class="btnSubmit" value="등록하기">
                            </div>
                        </form>
                    </article>
                </section>
            </div>
        </section>
<%@include file ="../_footer.jsp" %>