/*
 * util.h
 *
 *  Created on: 01.02.2019
 *      Author: psota
 */

#ifndef UTIL_H
#define UTIL_H

#include <cstddef>
#include <memory>
#include <type_traits>
#include <utility>

// util::make_unique
// Based on official std::make_unique proposal https://isocpp.org/files/papers/N3656.txt
// by Stephan T. Lavavej <stl@microsoft.com>

namespace util {
	namespace detail {
		template<typename T> 
		struct unique_if
		{
			using single_object = std::unique_ptr<T>;
		};

		template<typename T> 
		struct unique_if<T[]> 
		{
			using unbounded_array = std::unique_ptr<T[]>;
		};

		template<typename T, std::size_t N> 
		struct unique_if<T[N]> 
		{
			using bounded_array = void;
		};
	}

	template<typename T, typename... Args>
	typename detail::unique_if<T>::single_object make_unique(Args && ... args) 
	{
		return std::unique_ptr<T>(new T(std::forward<Args>(args)...));
	};

	template<typename T>
	typename detail::unique_if<T>::unbounded_array make_unique(std::size_t n)
	{
		using ArrayType = typename std::remove_extent<T>::type;
		return std::unique_ptr<T>(new ArrayType[n]());
	};

	template<typename T, typename... Args>
	typename detail::unique_if<T>::bounded_array make_unique(Args && ...) = delete;
}

namespace util {


#ifdef USE_STD_DECAY_T
	template<typename T>
	using decay_t = std::decay_t<T>;
#else
	template<typename T>
	using decay_t = typename std::decay<T>::type;
#endif
}


#endif