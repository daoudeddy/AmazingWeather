package com.github.amazingweather.core

// Used this class for a long time from an open source project.
// Mow it is widely adopted as a functional type monad
// Credits to Daniel Westheide, Fernando Cejas, Alex Hart & Arrow


/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [EitherErrorOr] are either an instance of [Left] or [Right].
 * FP Convention dictates that:
 *      [Left] is used for "failure".
 *      [Right] is used for "success".
 *
 * @see Left
 * @see Right
 */

sealed class EitherErrorOr<out R> {

    /** * Represents the left side of [EitherErrorOr] class which by convention is a "Failure". */
    data class Left(val a: Result.Error) : EitherErrorOr<Nothing>()

    /** * Represents the right side of [EitherErrorOr] class which by convention is a "Success". */
    data class Right<out R>(val b: R) : EitherErrorOr<R>()

    /**
     * Returns true if this is a Right, false otherwise.
     * @see Right
     */
    val isRight get() = this is Right<R>

    /**
     * Returns true if this is a Left, false otherwise.
     * @see Left
     */
    val isLeft get() = this is Left

    /**
     * Creates a Left type.
     * @see Left
     */
    fun left(a: Result.Error) =
        Left(a)

    /**
     * Creates a Right type.
     * @see Right
     */
    fun <R> right(b: R) =
        Right(b)


    /**
     * Applies onFailure if this is a Left or onSuccess if this is a Right.
     * @see Left
     * @see Right
     */
    inline fun fold(
        onFailure: (Result.Error) -> Any = {},
        onSuccess: (result: R) -> Any = {}
    ) {
        when (this) {
            is Left -> onFailure(a)
            is Right -> onSuccess(b)
        }
    }
}