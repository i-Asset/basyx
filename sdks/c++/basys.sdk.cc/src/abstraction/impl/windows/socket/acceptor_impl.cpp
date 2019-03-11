/*
 * socket_windows.cpp
 *
 *  Created on: 06.11.2018
 *      Author: schnicke
 */

#include "impl/acceptor_impl.h"
#include "impl/socket_impl.h"

#include "util/util.h"

#include <iostream>

namespace basyx {
	namespace net {
		namespace impl {

			acceptor_impl::~acceptor_impl()
			{
				this->shutdown(SHUTDOWN_RDWR);
				this->close();
			}


			int acceptor_impl::listen(std::string const& port) {
				WSADATA wsaData;

				// Initialize Winsock
				int iResult = WSAStartup(MAKEWORD(2, 2), &wsaData);
				if (iResult != 0) {
					std::cout << "Acceptor# WSAStartup failed: " << iResult
						<< std::endl;
				}

				struct addrinfo *result = NULL, hints;

				ZeroMemory(&hints, sizeof(hints));
				hints.ai_family = AF_INET; // Internet address family is unspecified so that either an IPv6 or IPv4 address can be returned
				hints.ai_socktype = SOCK_STREAM; // Requests the socket type to be a stream socket for the TCP protocol
				hints.ai_protocol = IPPROTO_TCP;
				hints.ai_flags = AI_PASSIVE;

				// Resolve the local address and port to be used by the server
				iResult = getaddrinfo(NULL, port.c_str(), &hints, &result);
				if (iResult != 0) {
					std::cout << "Acceptor# getaddrinfo failed: " << iResult
						<< std::endl;
					WSACleanup();
					return -1;
				}

				socketDesc = INVALID_SOCKET;

				// Create a SOCKET for the server to listen for client connections
				socketDesc = socket(result->ai_family, result->ai_socktype,
					result->ai_protocol);

				if (socketDesc == INVALID_SOCKET) {
					std::cout << "Acceptor# Error at socket(): "
						<< WSAGetLastError() << std::endl;
					freeaddrinfo(result);
					WSACleanup();
					return -1;
				}

				// Setup the TCP listening socket
				//bind the socket with the IP address and the port number.  In a way it is like the connect() function (the parameters are the same)
				iResult = bind(socketDesc, result->ai_addr, (int)result->ai_addrlen);

				if (iResult == SOCKET_ERROR) {
					std::cout << "Acceptor# bind failed:" << WSAGetLastError()
						<< std::endl;
					freeaddrinfo(result);
					closesocket(socketDesc);
					WSACleanup();
					return -1;
				}

				freeaddrinfo(result);

				// To listen on a socket
				// starts listening to allow clients to connect.
				if (::listen(socketDesc, SOMAXCONN) == SOCKET_ERROR) {
					std::cout << "BaSyxTCPServer# listen failed: " << WSAGetLastError()
						<< std::endl;
					closesocket(socketDesc);
					WSACleanup();
					return -1;
				}

				std::cout << "Acceptor# TCP Server Listening!" << std::endl;
				return 0;
			}

			std::unique_ptr<socket_impl> acceptor_impl::accept() {

				native_socket_type clientSock = ::accept(this->socketDesc, NULL, NULL);
				if (clientSock == INVALID_SOCKET) {
					std::cout << "Acceptor# accept failed: "
						<< WSAGetLastError() << std::endl;

					return nullptr;
				}
				else {
					return util::make_unique<socket_impl>(clientSock);
				}
			}

			int acceptor_impl::shutdown(enum SocketShutdownDir how) {
				return ::shutdown(socketDesc, how);
			}

			int acceptor_impl::close() {
				this->shutdown(SHUTDOWN_RDWR);

				if (::closesocket(this->socketDesc) != 0) {
					std::cout << "Acceptor# close() failed!" << std::endl;
					return -1;
				}
				this->socketDesc = 0;
				return 0;
			}

			int acceptor_impl::getErrorCode() {
				return WSAGetLastError();
			}

		}

	}
}