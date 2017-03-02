/**
 */
package apps.eclipse.util;

import apps.EnvironmentStorage;
import apps.ProjectLauncher;

import apps.docker.DockerProjectLauncher;
import apps.eclipse.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see apps.eclipse.EclipsePackage
 * @generated
 */
public class EclipseAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static EclipsePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EclipseAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = EclipsePackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EclipseSwitch<Adapter> modelSwitch =
		new EclipseSwitch<Adapter>() {
			@Override
			public Adapter caseEclipseEnvironmentStorage(EclipseEnvironmentStorage object) {
				return createEclipseEnvironmentStorageAdapter();
			}
			@Override
			public Adapter caseDockerPTPSyncProjectLauncher(DockerPTPSyncProjectLauncher object) {
				return createDockerPTPSyncProjectLauncherAdapter();
			}
			@Override
			public Adapter caseEnvironmentStorage(EnvironmentStorage object) {
				return createEnvironmentStorageAdapter();
			}
			@Override
			public Adapter caseProjectLauncher(ProjectLauncher object) {
				return createProjectLauncherAdapter();
			}
			@Override
			public Adapter caseDockerProjectLauncher(DockerProjectLauncher object) {
				return createDockerProjectLauncherAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link apps.eclipse.EclipseEnvironmentStorage <em>Environment Storage</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see apps.eclipse.EclipseEnvironmentStorage
	 * @generated
	 */
	public Adapter createEclipseEnvironmentStorageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link apps.eclipse.DockerPTPSyncProjectLauncher <em>Docker PTP Sync Project Launcher</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see apps.eclipse.DockerPTPSyncProjectLauncher
	 * @generated
	 */
	public Adapter createDockerPTPSyncProjectLauncherAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link apps.EnvironmentStorage <em>Environment Storage</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see apps.EnvironmentStorage
	 * @generated
	 */
	public Adapter createEnvironmentStorageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link apps.ProjectLauncher <em>Project Launcher</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see apps.ProjectLauncher
	 * @generated
	 */
	public Adapter createProjectLauncherAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link apps.docker.DockerProjectLauncher <em>Project Launcher</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see apps.docker.DockerProjectLauncher
	 * @generated
	 */
	public Adapter createDockerProjectLauncherAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //EclipseAdapterFactory