/*
 * Copyright 2014 Red Hat, Inc.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

package io.vertx.ext.auth;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

import java.util.Set;

/**
 * Represents an authenticate User and contains operations to authorise the user, using a role/permission
 * based model.
 * <p>
 * Please consult the documentation for a detailed explanation.
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
@VertxGen
public interface User {

  /**
   * Does the user have the specified role?
   *
   * @param role  the role
   * @param resultHandler  handler that will be called with an {@link io.vertx.core.AsyncResult} containing the value
   *                       `true` if the they have the role or `false` otherwise.
   * @return the User to enable fluent use
   */
  @Fluent
  @CacheReturn
  User hasRole(String role, Handler<AsyncResult<Boolean>> resultHandler);

  /**
   * Does the user have the specified permission?
   *
   * @param permission  the permission
   * @param resultHandler  handler that will be called with an {@link io.vertx.core.AsyncResult} containing the value
   *                       `true` if the they have the permission or `false` otherwise.
   * @return the User to enable fluent use
   */
  @Fluent
  @CacheReturn
  User hasPermission(String permission, Handler<AsyncResult<Boolean>> resultHandler);

  /**
   * Does the user have all the specified roles?
   *
   * @param roles  the set of roles
   * @param resultHandler  handler that will be called with an {@link io.vertx.core.AsyncResult} containing the value
   *                       `true` if the they have all the roles or `false` otherwise.
   * @return the User to enable fluent use
   */
  @Fluent
  @CacheReturn
  User hasRoles(Set<String> roles, Handler<AsyncResult<Boolean>> resultHandler);

  /**
   * Does the user have all the specified permissions?
   *
   * @param permissions  the set of permissions
   * @param resultHandler  handler that will be called with an {@link io.vertx.core.AsyncResult} containing the value
   *                       `true` if the they have all the permissions or `false` otherwise.
   * @return the User to enable fluent use
   */
  @Fluent
  @CacheReturn
  User hasPermissions(Set<String> permissions, Handler<AsyncResult<Boolean>> resultHandler);

  /**
   * The User object will cache any roles or permissions that it knows it has to avoid hitting the
   * underlying auth provider each time.  Use this method if you want to clear this cache.
   *
   * @return the User to enable fluent use
   */
  @Fluent
  @CacheReturn
  User clearCache();

  /**
   * Get the underlying principal for the User. What this actually returns depends on the implementation.
   * For a simple user/password based auth, it's likely to contain a JSON object with the following structure:
   * <pre>
   *   {
   *     "username", "tim"
   *   }
   * </pre>
   * @return
   */
  JsonObject principal();

  /**
   * Is the User clusterable? Some Apex handlers store the User in the Apex session so it is available between
   * requests and even on different servers in the case of clustered sessions.
   * If a User implementation should not be serialized and clustered then this should return `false`.
   *
   * @return true if the implementation is clusterable, `false` otherwise.
   */
  boolean isClusterable();
}
