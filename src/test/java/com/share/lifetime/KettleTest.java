package com.share.lifetime;

import java.sql.ResultSet;

import org.junit.Test;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.Result;
import org.pentaho.di.core.database.Database;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

public class KettleTest {

	@Test
	public void testKettleConnect() {
		try {
			KettleEnvironment.init();

			DatabaseMeta dbm = new DatabaseMeta();

			dbm.setDatabaseInterface(DatabaseMeta.getDatabaseInterface("MYSQL"));

			dbm.setName("DB Conn");

			dbm.setHostname("localhost");
			dbm.setDBName("admin");
			dbm.setDBPort("3306");
			dbm.setUsername("root");
			dbm.setPassword("root");

			Database db = new Database(null, dbm);

			db.connect();

			ResultSet rs = db.openQuery("select * from user");

			if (rs != null) {

				int colCount = rs.getMetaData().getColumnCount();

				for (int i = 1; i < colCount; i++) {
					String columnName = rs.getMetaData().getColumnName(i);
					System.out.print("|" + columnName);
				}
				System.out.println("");

				while (rs.next()) {
					for (int i = 1; i < colCount; i++) {
						System.out.print("|" + rs.getString(i));
					}
					System.out.println("");
				}

				db.closeQuery(rs);
			}
			db.disconnect();

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @see https://wiki.pentaho.com/display/EAI/Working+with+a+PDI+repository
	 * @see https://wiki.pentaho.com/display/EAI/Executing+a+PDI+job
	 * @throws KettleException
	 */
	@Test
	public void testKettleJob() throws KettleException {
		// RepositoriesMeta repositoriesMeta = new RepositoriesMeta();
		// repositoriesMeta.readData();
		// RepositoryMeta repositoryMeta = repositoriesMeta.findRepository(
		// repositoryName );
		// PluginRegistry registry = PluginRegistry.getInstance();
		// Repository repository = registry.loadClass(
		// RepositoryPluginType.class,
		// repositoryMeta,
		// Repository.class
		// );
		// repository.init(repositoryMeta);
		// repository.connect(username, password);

		// JobMeta jobMeta = new JobMeta("/foo/bar/job.kjb", repository);
		// Job job = new Job(repository, jobMeta);
		// job.start();
		// job.waitUntilFinished();
		// Result result = job.getResult();

		KettleEnvironment.init();
		JobMeta jobMeta = new JobMeta("/job.kjb", null);
		Job job = new Job(null, jobMeta);
		// job.setArguments(arguments);
		job.start();
		job.waitUntilFinished();
		Result result = job.getResult();

	}

	@Test
	public void testKettleTransMeta() throws KettleException {

		KettleEnvironment.init();

		// TransMeta transMeta = new TransMeta("/foo/bar/trans.ktr",
		// repository);//X
		TransMeta transMeta = new TransMeta("/trans.ktr");

		Trans trans = new Trans(transMeta);

		// The following will run the transformation in a separate thread.
		//
		// trans.execute(arguments);
		trans.execute(null);

		// If you want to wait until the transformation is finished...
		//
		trans.waitUntilFinished(); //

		// If you want to know about the execution result.
		//
		Result result = trans.getResult();

	}

	/**
	 * @see https://wiki.pentaho.com/display/EAI/Pentaho+Data+Integration+-+Java+API+Examples
	 * 
	 * @param filename
	 */
	public static void runTransformation(String filename) {
		try {
			// StepLoader.init();
			EnvUtil.environmentInit();
			TransMeta transMeta = new TransMeta(filename);
			Trans trans = new Trans(transMeta);

			trans.execute(null); // You can pass arguments instead of null.
			trans.waitUntilFinished();
			if (trans.getErrors() > 0) {
				throw new RuntimeException("There were errors during transformation execution.");
			}
		} catch (KettleException e) {
			// TODO Put your exception-handling code here.
			System.out.println(e);
		}
	}
	

}
