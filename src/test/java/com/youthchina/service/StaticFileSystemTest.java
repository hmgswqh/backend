package com.youthchina.service;

/*
@SpringBootTest
@RunWith(SpringRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:users.xml"})
@WebAppConfiguration
public class StaticFileSystemTest {
    @Autowired
    private StaticFileSystemMapper staticFileSystemMapper;
    @Autowired
    private FileNameGenerate fileNameGenerate;
    @Autowired
    private AliCloudFileStorageService aliCloudFileStorageService;

    @Autowired
    private SnowFlakeIdGenerate snowFlakeIdGenerate;
    */
    /*
   @Test
    public void testUploadFile() throws IOException {
       FileStorageService[] fileStorageService = {aliCloudFileStorageService};
       StaticFileService staticFileService = new StaticFileService(staticFileSystemMapper,fileNameGenerate,fileStorageService,snowFlakeIdGenerate);
      staticFileService.saveFile((Resource) new File("D:\\video3.mp4"),2);
   }
   */
/*
    @Test
    public void testDownload() throws IOException {
        FileStorageService[] fileStorageService = {aliCloudFileStorageService};
        StaticFileService staticFileService = new StaticFileService(staticFileSystemMapper, fileNameGenerate, fileStorageService, snowFlakeIdGenerate);
        URL url = staticFileService.getFileUrl("2856306669745344512", "China");
        System.out.println(url);
    }

    @Test
    public void testSaveInfo() {
        staticFileSystemMapper.setCloudStorageId("DOCU_SERVER_ALI_ID", "55555555555", "2854956924430979072");
    }
}
*/