//package dtu.library.app;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//
//public class MockEmailServerHolder {
//
//	private EmailServer emailServer = mock(EmailServer.class);
//	private Helper helper;
//
//	public MockEmailServerHolder(LibraryApp libraryApp) throws Exception {
//		verifyEmail();
//		libraryApp.setEmailServer(emailServer);
//	}
//
//	public void verifyEmail() throws Exception {
//		verify(emailServer).sendMail(helper.getUser(), 1);
//	}
//
//}
